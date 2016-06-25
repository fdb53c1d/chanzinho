package org.chanzinho.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.chanzinho.exception.ChanzinhoException;
import org.chanzinho.model.Board;
import org.chanzinho.model.Post;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostProcessor {

  @Autowired
  Environment env;
  @Autowired
  BoardService boardService;
  @Autowired
  PostService postService;

  /**
   * <pre>
   * Verifies the following:
   * -post parameters (boardName, replyThread, postPassword) are not empty,
   * -there's at least a file/message
   * -there's a file for OP (if required)
   * -thread and board are valid
   * -post number is from a thread
   * </pre>
   */
  public void validatePost(String boardName, String replyThread, String email, String name,
      String subject, String message, String postPassword, MultipartFile imageFile,
      HttpServletRequest request) throws ChanzinhoException {

    if (boardName.trim().isEmpty() || replyThread.trim().isEmpty()) {
      throw new ChanzinhoException("Falha ao enviar campos.");
    }

    if (postPassword.trim().isEmpty()) {
      throw new ChanzinhoException("Preencha o campo de senha.");
    }

    if (message.trim().isEmpty() && imageFile.isEmpty()) {
      throw new ChanzinhoException("Uma imagem ou mensagem deve ser enviada.");
    }

    Board board = boardService.findByName(boardName);

    if (board == null) {
      throw new ChanzinhoException("Board inválida.");
    }

    Long threadNum;

    try {
      threadNum = Long.parseLong(replyThread);
    } catch (NumberFormatException e) {
      throw new ChanzinhoException("Thread inválida.");
    }

    if (imageFile.isEmpty() && threadNum == 0 && !board.getEnableNoFile()) {
      throw new ChanzinhoException("Um arquivo é necessário para criação de threads.");
    }

    if (threadNum != 0) {
      Post thread = postService.findByBoardIdAndPostId(board.getId(), threadNum);
      if (thread.getParentId() != 0) {
        throw new ChanzinhoException("Thread inválida.");
      }
    }
  }


  /**
   * <pre>
   * File processing:
   * -checks if file type is supported, then saves it on source folder
   * -sets file parameters
   * -generates thumbnail
   * </pre>
   */
  // TODO generate hash, include thumbnail type field
  public Post processFile(MultipartFile multiPartFile, Board board, Post post, Long timestamp)
      throws ChanzinhoException {

    if (multiPartFile.isEmpty()) {
      post.setFile("");
      post.setFileMd5("");
      post.setFileType("");
      post.setFileOriginal("");
      post.setFileSize(0);
      post.setFileSizeFormatted("");
      post.setImageW(0);
      post.setImageH(0);
      post.setThumbW(0);
      post.setThumbH(0);
      return post;
    }


    String fileType;

    if (multiPartFile.getContentType().equals("image/jpg")
        || multiPartFile.getContentType().equals("image/jpeg")) {
      fileType = "jpg";
    } else if (multiPartFile.getContentType().equals("image/png")) {
      fileType = "png";
    } else if (multiPartFile.getContentType().equals("image/gif")) {
      fileType = "gif";
    } else if (multiPartFile.getContentType().equals("video/webm")) {
      fileType = "webm";
    } else if (multiPartFile.getContentType().equals("video/mp4")) {
      fileType = "mp4";
    } else {
      throw new ChanzinhoException("Tipo de arquivo não suportado.");
    }

    long fileSize = multiPartFile.getSize();

    final String[] units = new String[] {"B", "kB", "MB", "GB", "TB"};
    int digitGroups = (int) (Math.log10(fileSize) / Math.log10(1024));
    DecimalFormat decimalFormat = new DecimalFormat("####.##");
    DecimalFormatSymbols dfs = decimalFormat.getDecimalFormatSymbols();
    dfs.setDecimalSeparator('.');
    decimalFormat.setDecimalFormatSymbols(dfs);
    String fileSizeFormatted =
        decimalFormat.format(fileSize / Math.pow(1024, digitGroups)) + " " + units[digitGroups];

    post.setFile(String.valueOf(timestamp) + String.format("%03d", new Random().nextInt(99)));
    post.setFileType(fileType);
    post.setFileOriginal(FilenameUtils.getBaseName(multiPartFile.getOriginalFilename()));
    post.setFileSize(new Long(fileSize).intValue());
    post.setFileSizeFormatted(fileSizeFormatted);
    
    String filePath = "resources/" + board.getName() + "/src/" + post.getFile() + "." + fileType;
    File file;

    try {
      file = new File(filePath);
      multiPartFile.transferTo(file.getAbsoluteFile());
    } catch (IOException e) {
      throw new ChanzinhoException("Falha ao processar arquivo: " + e.getMessage());
    }

    if (fileType.equals("webm") || fileType.equals("mp4")) {
      try {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file);
        grabber.start();
        ToIplImage converter = new ToIplImage();
        IplImage grabbedImage = converter.convert(grabber.grabImage());
        int width = grabbedImage.width();
        int height = grabbedImage.height();

        post.setImageW(width);
        post.setImageH(height);

        IplImage thumbnail;
        Frame frameThumb;


        if (Math.max(width, height) <= 200) {
          thumbnail = IplImage.create(width, height, grabbedImage.depth(), 1);
          post.setThumbW(width);
          post.setThumbH(height);

          frameThumb = converter.convert(grabbedImage);
        } else {
          int max = Math.max(width, height);
          if (max == height) {
            int thumbWidth = new Double(((double) width) * ((double) 200.0 / height)).intValue();
            thumbnail =
                IplImage.create(thumbWidth, 200, grabbedImage.depth(), grabbedImage.nChannels());
            post.setThumbH(200);
            post.setThumbW(thumbWidth);
          } else {
            int thumbHeight = new Double(((double) height) * ((double) 200.0 / width)).intValue();
            thumbnail =
                IplImage.create(200, thumbHeight, grabbedImage.depth(), grabbedImage.nChannels());
            post.setThumbH(thumbHeight);
            post.setThumbW(200);
          }
          org.bytedeco.javacpp.opencv_imgproc.cvResize(grabbedImage, thumbnail);

          frameThumb = converter.convert(thumbnail);
        }

        ImageIO.write(new Java2DFrameConverter().convert(frameThumb), "jpg",
            new File("resources/" + board.getName() + "/thumb/" + post.getFile() + "s.jpg"));


        BufferedImage image = new Java2DFrameConverter().convert(grabber.grabImage());

        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.addImage();
        if (Math.max(height, width) <= 200) {
          op.thumbnail(width, height);
          post.setThumbH(height);
          post.setThumbW(width);
        } else {
          int max = Math.max(height, width);
          if (max == height) {
            int thumbWidth = new Double(((double) width) * ((double) 200.0 / height)).intValue();
            op.thumbnail(thumbWidth, 200);
            post.setThumbH(200);
            post.setThumbW(thumbWidth);
          } else {
            int thumbHeight = new Double(((double) height) * ((double) 200.0 / width)).intValue();
            op.thumbnail(200, thumbHeight);
            post.setThumbH(thumbHeight);
            post.setThumbW(200);
          }
        }
        op.addImage();
        try {
          cmd.run(op, image, "resources/" + board.getName() + "/thumb/" + post.getFile() + "s.jpg");
        } catch (Exception e) {
          throw new ChanzinhoException(
              "Falha ao processar thumbnail do arquivo: " + e.getMessage());
        }

      } catch (FrameGrabber.Exception e) {
        throw new ChanzinhoException("Erro ao gerar thumbnail do arquivo: " + e.getMessage());
      } catch (IOException e) {
        throw new ChanzinhoException("Erro ao gerar thumbnail do arquivo: " + e.getMessage());
      }
    }

    if (fileType.equals("jpg") || fileType.equals("png") || fileType.equals("gif")) {
      BufferedImage image;

      try {
        image = ImageIO.read(FileUtils.openInputStream(file));
      } catch (IOException e) {
        throw new ChanzinhoException("Falha ao processar arquivo: " + e.getMessage());
      }

      int imageH = image.getHeight();
      int imageW = image.getWidth();

      post.setImageH(imageH);
      post.setImageW(imageW);

      ConvertCmd cmd = new ConvertCmd();
      IMOperation op = new IMOperation();
      if (fileType == "gif") {
        op.addImage(filePath + "[0]");
      } else {
        op.addImage(filePath);
      }
      if (Math.max(imageH, imageW) <= 200) {
        op.thumbnail(imageW, imageH);
        post.setThumbH(imageH);
        post.setThumbW(imageW);
      } else {
        int max = Math.max(imageH, imageW);
        if (max == imageH) {
          int width = new Double(((double) imageW) * ((double) 200.0 / imageH)).intValue();
          op.thumbnail(width, 200);
          post.setThumbH(200);
          post.setThumbW(width);
        } else {
          int height = new Double(((double) imageH) * ((double) 200.0 / imageW)).intValue();
          op.thumbnail(200, height);
          post.setThumbH(height);
          post.setThumbW(200);
        }
      }
      op.addImage("resources/" + board.getName() + "/thumb/" + post.getFile() + "s." + fileType);
      try {
        cmd.run(op);
      } catch (Exception e) {
        throw new ChanzinhoException("Falha ao processar thumbnail do arquivo: " + e.getMessage());
      }
    }

    post.setFileMd5("asd");

    return post;
  }

  public List<String> deletePosts(Integer boardId, List<String> postIds, String delPassword,
      String fileOnly) {
    List<String> ret = new ArrayList<String>();
    for (String s : postIds) {
      Long postId;
      try {
        postId = Long.parseLong(s);
      } catch (NumberFormatException e) {
        ret.add("Post Inválido");
        continue;
      }
      ret.add(postService.deletePost(boardId, postId, delPassword, fileOnly));
    }
    return ret;
  }

}
