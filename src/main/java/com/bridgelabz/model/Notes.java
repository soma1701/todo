package com.bridgelabz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="notes", schema="soma")
public class Notes {
	
	@Id  
	 @GeneratedValue(strategy=GenerationType.AUTO, generator="mygen")
	 @GenericGenerator(name="mygen",strategy="native")
	 @Column(name = "notes_id")  
	 private int notesId;
	 
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="creadted_time")
	private Date createdTime;
	
	@Column(name="is_pin")
	private boolean isPinned;
	
	/*@Column(name="is_archive")
	private boolean isArchieved;
	
	@Column(name="is_trashed")
	private boolean isTrashed;
	
	@Column(name="color")
	private String color; 
	
	@Column(name="reminder")
	private Date reminder;*/
	

	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	User user;
	
	public int getNotesId() {
		return notesId;
	}

	public void setNotesId(int notesId) {
		this.notesId = notesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}


	

}
