package org.chanzinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "banlist")
public class BanList {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(name = "type")
  private Integer type;
  @Column(name = "expired")
  private Integer expired;
  @Column(name = "allowread")
  private Integer allowRead;
  @Column(name = "ip")
  private String ip;
  @Column(name = "ipmd5")
  private String ipMd5;
  @Column(name = "globalban")
  private Integer globalBan;
  @Column(name = "boards")
  private String boards;
  @Column(name = "by")
  private String by;
  @Column(name = "at")
  private Integer at;
  @Column(name = "until")
  private Integer until;
  @Column(name = "reason")
  private String reason;
  @Column(name = "staffnote")
  private String staffNote;
  @Column(name = "appeal")
  private String appeal;
  @Column(name = "appealat")
  private Integer appealAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getExpired() {
    return expired;
  }

  public void setExpired(Integer expired) {
    this.expired = expired;
  }

  public Integer getAllowRead() {
    return allowRead;
  }

  public void setAllowRead(Integer allowRead) {
    this.allowRead = allowRead;
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

  public Integer getGlobalBan() {
    return globalBan;
  }

  public void setGlobalBan(Integer globalBan) {
    this.globalBan = globalBan;
  }

  public String getBoards() {
    return boards;
  }

  public void setBoards(String boards) {
    this.boards = boards;
  }

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
  }

  public Integer getAt() {
    return at;
  }

  public void setAt(Integer at) {
    this.at = at;
  }

  public Integer getUntil() {
    return until;
  }

  public void setUntil(Integer until) {
    this.until = until;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getStaffNote() {
    return staffNote;
  }

  public void setStaffNote(String staffNote) {
    this.staffNote = staffNote;
  }

  public String getAppeal() {
    return appeal;
  }

  public void setAppeal(String appeal) {
    this.appeal = appeal;
  }

  public Integer getAppealAt() {
    return appealAt;
  }

  public void setAppealAt(Integer appealAt) {
    this.appealAt = appealAt;
  }

  @Override
  public String toString() {
    return "BanList [id=" + id + ", type=" + type + ", expired=" + expired + ", allowRead="
        + allowRead + ", ip=" + ip + ", ipMd5=" + ipMd5 + ", globalBan=" + globalBan + ", boards="
        + boards + ", by=" + by + ", at=" + at + ", until=" + until + ", reason=" + reason
        + ", staffNote=" + staffNote + ", appeal=" + appeal + ", appealAt=" + appealAt + "]";
  }
}
