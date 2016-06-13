package org.chanzinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modlog")
public class ModLog {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="entry")
	private String entry;
	@Column(name="user")
	private String user;
	@Column(name="category")
	private Integer category;
	@Column(name="timestamp")
	private Integer timpestamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getTimpestamp() {
		return timpestamp;
	}
	public void setTimpestamp(Integer timpestamp) {
		this.timpestamp = timpestamp;
	}
	
	@Override
	public String toString() {
		return "ModLog [id=" + id + ", entry=" + entry + ", user=" + user + ", category=" + category + ", timpestamp="
				+ timpestamp + "]";
	}
}
