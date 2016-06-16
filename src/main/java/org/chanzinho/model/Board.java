package org.chanzinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boards")
public class Board {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(name = "order")
  private Integer order;
  @Column(name = "name")
  private String name;
  @Column(name = "type")
  private Integer type;
  @Column(name = "start")
  private Integer start;
  @Column(name = "uploadtype")
  private Integer uploadType;
  @Column(name = "desc")
  private String desc;
  @Column(name = "image")
  private String image;
  @Column(name = "section")
  private Integer section;
  @Column(name = "maximagesize")
  private Integer maxImageSize;
  @Column(name = "maxpages")
  private Integer maxPages;
  @Column(name = "maxage")
  private Integer maxAge;
  @Column(name = "markpage")
  private Integer markPage;
  @Column(name = "maxreplies")
  private Integer maxReplies;
  @Column(name = "messagelength")
  private Integer messageLength;
  @Column(name = "createdon")
  private Integer createdOn;
  @Column(name = "locked")
  private Integer locked;
  @Column(name = "includeheader")
  private String includeHeader;
  @Column(name = "redirecttothread")
  private Integer redirectToThread;
  @Column(name = "anonymous")
  private String anonymous;
  @Column(name = "forcedanon")
  private Integer forcedAnon;
  @Column(name = "embeds_allowed")
  private String embedsAllowed;
  @Column(name = "trial")
  private Integer trial;
  @Column(name = "popular")
  private Integer popular;
  @Column(name = "defaultstyle")
  private String defaultStyle;
  @Column(name = "locale")
  private String locale;
  @Column(name = "showid")
  private Integer showId;
  @Column(name = "compactlist")
  private Integer compactList;
  @Column(name = "enablereporting")
  private Integer enableReporting;
  @Column(name = "enablecaptcha")
  private Integer enableCaptcha;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getUploadType() {
    return uploadType;
  }

  public void setUploadType(Integer uploadType) {
    this.uploadType = uploadType;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getSection() {
    return section;
  }

  public void setSection(Integer section) {
    this.section = section;
  }

  public Integer getMaxImageSize() {
    return maxImageSize;
  }

  public void setMaxImageSize(Integer maxImageSize) {
    this.maxImageSize = maxImageSize;
  }

  public Integer getMaxPages() {
    return maxPages;
  }

  public void setMaxPages(Integer maxPages) {
    this.maxPages = maxPages;
  }

  public Integer getMaxAge() {
    return maxAge;
  }

  public void setMaxAge(Integer maxAge) {
    this.maxAge = maxAge;
  }

  public Integer getMarkPage() {
    return markPage;
  }

  public void setMarkPage(Integer markPage) {
    this.markPage = markPage;
  }

  public Integer getMaxReplies() {
    return maxReplies;
  }

  public void setMaxReplies(Integer maxReplies) {
    this.maxReplies = maxReplies;
  }

  public Integer getMessageLength() {
    return messageLength;
  }

  public void setMessageLength(Integer messageLength) {
    this.messageLength = messageLength;
  }

  public Integer getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Integer createdOn) {
    this.createdOn = createdOn;
  }

  public Integer getLocked() {
    return locked;
  }

  public void setLocked(Integer locked) {
    this.locked = locked;
  }

  public String getIncludeHeader() {
    return includeHeader;
  }

  public void setIncludeHeader(String includeHeader) {
    this.includeHeader = includeHeader;
  }

  public Integer getRedirectToThread() {
    return redirectToThread;
  }

  public void setRedirectToThread(Integer redirectToThread) {
    this.redirectToThread = redirectToThread;
  }

  public String getAnonymous() {
    return anonymous;
  }

  public void setAnonymous(String anonymous) {
    this.anonymous = anonymous;
  }

  public Integer getForcedAnon() {
    return forcedAnon;
  }

  public void setForcedAnon(Integer forcedAnon) {
    this.forcedAnon = forcedAnon;
  }

  public String getEmbedsAllowed() {
    return embedsAllowed;
  }

  public void setEmbedsAllowed(String embedsAllowed) {
    this.embedsAllowed = embedsAllowed;
  }

  public Integer getTrial() {
    return trial;
  }

  public void setTrial(Integer trial) {
    this.trial = trial;
  }

  public Integer getPopular() {
    return popular;
  }

  public void setPopular(Integer popular) {
    this.popular = popular;
  }

  public String getDefaultStyle() {
    return defaultStyle;
  }

  public void setDefaultStyle(String defaultStyle) {
    this.defaultStyle = defaultStyle;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public Integer getShowId() {
    return showId;
  }

  public void setShowId(Integer showId) {
    this.showId = showId;
  }

  public Integer getCompactList() {
    return compactList;
  }

  public void setCompactList(Integer compactList) {
    this.compactList = compactList;
  }

  public Integer getEnableReporting() {
    return enableReporting;
  }

  public void setEnableReporting(Integer enableReporting) {
    this.enableReporting = enableReporting;
  }

  public Integer getEnableCaptcha() {
    return enableCaptcha;
  }

  public void setEnableCaptcha(Integer enableCaptcha) {
    this.enableCaptcha = enableCaptcha;
  }

  @Override
  public String toString() {
    return "Board [id=" + id + ", order=" + order + ", name=" + name + ", type=" + type + ", start="
        + start + ", uploadType=" + uploadType + ", desc=" + desc + ", image=" + image
        + ", section=" + section + ", maxImageSize=" + maxImageSize + ", maxPages=" + maxPages
        + ", maxAge=" + maxAge + ", markPage=" + markPage + ", maxReplies=" + maxReplies
        + ", messageLength=" + messageLength + ", createdOn=" + createdOn + ", locked=" + locked
        + ", includeHeader=" + includeHeader + ", redirectToThread=" + redirectToThread
        + ", anonymous=" + anonymous + ", forcedAnon=" + forcedAnon + ", embedsAllowed="
        + embedsAllowed + ", trial=" + trial + ", popular=" + popular + ", defaultStyle="
        + defaultStyle + ", locale=" + locale + ", showId=" + showId + ", compactList="
        + compactList + ", enableReporting=" + enableReporting + ", enableCaptcha=" + enableCaptcha
        + "]";
  }


}
