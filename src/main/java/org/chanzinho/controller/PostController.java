package org.chanzinho.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Instant;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.chanzinho.model.Post;
import org.chanzinho.service.BoardService;
import org.chanzinho.service.PostService;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.HtmlUtils;

@RestController
@EnableWebMvc
public class PostController {
	
	@Autowired
	BoardService boardService;
	@Autowired
	PostService postService;

	@RequestMapping(value="/board.php", method=RequestMethod.POST)
	public String postHandler(@RequestParam("board") String board,
			@RequestParam("replythread") String replyThread,
			@RequestParam("em") String email,
			@RequestParam("name") String name,
			@RequestParam("subject") String subject,
			@RequestParam("message") String message,
			@RequestParam("postpassword") String postPassword,
			@RequestParam("imagefile") MultipartFile imageFile,
			HttpServletRequest request) {
		
		Post post = new Post();
		
		if(Integer.parseInt(replyThread) == 0) {
			if(imageFile == null || imageFile.isEmpty()) {
				return "poste uma imagem";
			}
			if(postPassword.equals("")) {
				return "use uma senha";
			}
			post.setBoardId(boardService.findByName(board).getId());
			Long timestamp = Instant.now().getEpochSecond();
			post.setBumped(timestamp.intValue());
			post.setDeletedTimestamp(0);
			post.setEmail(email);
			post.setFile(String.valueOf(timestamp) + String.format("%03d", new Random().nextInt(99)));
			post.setIp(request.getRemoteAddr());
			post.setIsDeleted(0);
			post.setLocked(0);
			post.setMessage(HtmlUtils.htmlEscape(message).replaceAll("\n", "<br>"));
			post.setName(name);
			post.setParentId(0L);
			post.setPassword(postPassword);
			post.setPosterAuthority(0);
			post.setReviewed(0);
			post.setStickied(0);
			post.setSubject(subject);
			post.setTag("");
			post.setTimestamp(timestamp.intValue());
			post.setTripcode("");
			
			BufferedImage image;
			
			try {
				image = ImageIO.read(imageFile.getInputStream());
			} catch(Exception e) {
				e.printStackTrace();
				return "deu errado";
			}
			
			post.setFileSize(new Long(imageFile.getSize()).intValue());
			post.setFileSizeFormatted(String.valueOf(new Long(imageFile.getSize())));
			String fileType;
			if(imageFile.getContentType().equals("image/jpg")
					|| imageFile.getContentType().equals("image/jpeg")) {
				fileType = "jpg";
			} else if(imageFile.getContentType().equals("image/png")) {
				fileType = "png";
			} else if(imageFile.getContentType().equals("image/gif")) {
				fileType = "gif";
			} else {
				return "tipo de arquivo nao suportado";
			}
			
			String filePath = "resources/" + board + "/src/" + post.getFile() + "." + fileType;
			File file;
			
			try {
				file = new File(filePath);
				imageFile.transferTo(file.getAbsoluteFile());
			} catch(Exception e) {
				e.printStackTrace();
				return "deu erro";
			}
			int imageH = image.getHeight();
			int imageW = image.getWidth();
			
			post.setFileType(fileType);
			post.setImageH(imageH);
			post.setImageW(imageW);
			post.setFileOriginal(FilenameUtils.getBaseName(imageFile.getOriginalFilename()));
			
			ConvertCmd cmd = new ConvertCmd();
			IMOperation op = new IMOperation();
			op.addImage(filePath);
			if(Math.max(imageH, imageW) <= 200) {
				op.thumbnail(imageW, imageH);
				post.setThumbH(imageH);
				post.setThumbW(imageW);
			} else {
				int max = Math.max(imageH, imageW);
				if(max == imageH) {
					int width = new Double(((double) imageW)*((double) 200.0/imageH)).intValue();
					op.thumbnail(width, 200);
					post.setThumbH(200);
					post.setThumbW(width);
				} else {
					int height = new Double(((double) imageH)*((double) 200.0/imageW)).intValue();
					op.thumbnail(200, height);
					post.setThumbH(height);
					post.setThumbW(200);
				}
			}
			op.addImage("resources/" + board + "/thumb/" + post.getFile() + "s." + fileType);
			try {
				cmd.run(op);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		post.setFileMd5("");
		post.setIpMd5("");
		
		postService.save(post);
		
		return post.toString();
	}
	
}
