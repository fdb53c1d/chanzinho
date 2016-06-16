package org.chanzinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sections")
public class Section {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(name = "order")
  private Integer order;
  @Column(name = "hidden")
  private Integer hidden;
  @Column(name = "name")
  private String name;
  @Column(name = "abbreviation")
  private String abbreviation;

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

  public Integer getHidden() {
    return hidden;
  }

  public void setHidden(Integer hidden) {
    this.hidden = hidden;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  @Override
  public String toString() {
    return "Section [id=" + id + ", order=" + order + ", hidden=" + hidden + ", name=" + name
        + ", abbreviation=" + abbreviation + "]";
  }
}
