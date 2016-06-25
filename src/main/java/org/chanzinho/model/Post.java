package org.chanzinho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQuery(name = "Post.insertPost", procedureName = "InsertPost", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_boardid", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_parentid", type = Long.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_name", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_tripcode", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_subject", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_message", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_password", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_file", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_file_md5", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_file_type", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_file_original",
        type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_file_size", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_file_size_formatted",
        type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_image_w", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_image_h", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_thumb_w", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_thumb_h", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_ip", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_ipmd5", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_tag", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_timestamp", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_stickied", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_locked", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_posterauthority",
        type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_reviewed", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_deleted_timestamp",
        type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_IS_DELETED", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_bumped", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id_out", type = Long.class)})
@Entity
@Table(name = "posts")
public class Post implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "postid")
  private Long postId;
  @Column(name = "boardid")
  private Integer boardId;
  @Column(name = "parentid")
  private Long parentId;
  @Column(name = "name")
  private String name;
  @Column(name = "tripcode")
  private String tripcode;
  @Column(name = "email")
  private String email;
  @Column(name = "subject")
  private String subject;
  @Column(name = "message")
  private String message;
  @Column(name = "password")
  private String password;
  @Column(name = "file")
  private String file;
  @Column(name = "file_md5")
  private String fileMd5;
  @Column(name = "file_type")
  private String fileType;
  @Column(name = "file_original")
  private String fileOriginal;
  @Column(name = "file_size")
  private Integer fileSize;
  @Column(name = "file_size_formatted")
  private String fileSizeFormatted;
  @Column(name = "image_w")
  private Integer imageW;
  @Column(name = "image_h")
  private Integer imageH;
  @Column(name = "thumb_w")
  private Integer thumbW;
  @Column(name = "thumb_h")
  private Integer thumbH;
  @Column(name = "ip")
  private String ip;
  @Column(name = "ipmd5")
  private String ipMd5;
  @Column(name = "tag")
  private String tag;
  @Column(name = "timestamp")
  private Integer timestamp;
  @Column(name = "stickied")
  private Integer stickied;
  @Column(name = "locked")
  private Integer locked;
  @Column(name = "posterauthority")
  private Integer posterAuthority;
  @Column(name = "reviewed")
  private Integer reviewed;
  @Column(name = "deleted_timestamp")
  private Integer deletedTimestamp;
  @Column(name = "IS_DELETED")
  private Integer isDeleted;
  @Column(name = "bumped")
  private Integer bumped;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Integer getBoardId() {
    return boardId;
  }

  public void setBoardId(Integer boardId) {
    this.boardId = boardId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTripcode() {
    return tripcode;
  }

  public void setTripcode(String tripcode) {
    this.tripcode = tripcode;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  public String getFileMd5() {
    return fileMd5;
  }

  public void setFileMd5(String fileMd5) {
    this.fileMd5 = fileMd5;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFileOriginal() {
    return fileOriginal;
  }

  public void setFileOriginal(String fileOriginal) {
    this.fileOriginal = fileOriginal;
  }

  public Integer getFileSize() {
    return fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileSizeFormatted() {
    return fileSizeFormatted;
  }

  public void setFileSizeFormatted(String fileSizeFormatted) {
    this.fileSizeFormatted = fileSizeFormatted;
  }

  public Integer getImageW() {
    return imageW;
  }

  public void setImageW(Integer imageW) {
    this.imageW = imageW;
  }

  public Integer getImageH() {
    return imageH;
  }

  public void setImageH(Integer imageH) {
    this.imageH = imageH;
  }

  public Integer getThumbW() {
    return thumbW;
  }

  public void setThumbW(Integer thumbW) {
    this.thumbW = thumbW;
  }

  public Integer getThumbH() {
    return thumbH;
  }

  public void setThumbH(Integer thumbH) {
    this.thumbH = thumbH;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getIpMd5() {
    return ipMd5;
  }

  public void setIpMd5(String ipMd5) {
    this.ipMd5 = ipMd5;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  public Integer getStickied() {
    return stickied;
  }

  public void setStickied(Integer stickied) {
    this.stickied = stickied;
  }

  public Integer getLocked() {
    return locked;
  }

  public void setLocked(Integer locked) {
    this.locked = locked;
  }

  public Integer getPosterAuthority() {
    return posterAuthority;
  }

  public void setPosterAuthority(Integer posterAuthority) {
    this.posterAuthority = posterAuthority;
  }

  public Integer getReviewed() {
    return reviewed;
  }

  public void setReviewed(Integer reviewed) {
    this.reviewed = reviewed;
  }

  public Integer getDeletedTimestamp() {
    return deletedTimestamp;
  }

  public void setDeletedTimestamp(Integer deletedTimestamp) {
    this.deletedTimestamp = deletedTimestamp;
  }

  public Integer getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getBumped() {
    return bumped;
  }

  public void setBumped(Integer bumped) {
    this.bumped = bumped;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Date getDate() {
    return new Date((timestamp.longValue()) * 1000L);
  }

  @Override
  public String toString() {
    return "Post [id=" + id + ", PostId=" + postId + ", boardId=" + boardId + ", parentId="
        + parentId + ", name=" + name + ", tripcode=" + tripcode + ", email=" + email + ", subject="
        + subject + ", message=" + message + ", password=" + password + ", file=" + file
        + ", fileMd5=" + fileMd5 + ", fileType=" + fileType + ", fileOriginal=" + fileOriginal
        + ", fileSize=" + fileSize + ", fileSizeFormatted=" + fileSizeFormatted + ", imageW="
        + imageW + ", imageH=" + imageH + ", thumbW=" + thumbW + ", thumbH=" + thumbH + ", ip=" + ip
        + ", ipMd5=" + ipMd5 + ", tag=" + tag + ", timestamp=" + timestamp + ", stickied="
        + stickied + ", locked=" + locked + ", posterAuthority=" + posterAuthority + ", reviewed="
        + reviewed + ", deletedTimestamp=" + deletedTimestamp + ", isDeleted=" + isDeleted
        + ", bumped=" + bumped + "]";
  }
}
