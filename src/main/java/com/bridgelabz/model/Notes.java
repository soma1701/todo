package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	
	@Column(name="is_archive")
	private boolean isArchived;
	
	@Column(name="is_trashed")
	private boolean isTrashed;
	
	@Column(name="color")
	private String color; 
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private String image;
	/*@Column(name="reminder")
	private Date reminder;*/
	

	@ManyToMany
	
	@JoinTable(name = "note_user", joinColumns = { @JoinColumn(name = "note_id") },
									inverseJoinColumns = {@JoinColumn(name = "id") })
	@JsonIgnore
	List<User> alUser = new ArrayList<>();
	
	@ManyToMany/*(cascade = CascadeType.REFRESH)*/
	@JoinTable(name = "note_label", joinColumns = { @JoinColumn(name = "note_id") },
									inverseJoinColumns = {@JoinColumn(name = "label_id") })
	@JsonIgnore
	private Set<Labels> alLabels = new HashSet<>();
	
	public Set<Labels> getLabels() {
		return alLabels;
	}

	public void setLabels(Set<Labels> alLabels) {
		this.alLabels = alLabels;
	}

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
	public List<User> getUser() {
		return alUser;
	}

	public void setUser(List<User> user) {
		this.alUser = user;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean getIsArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchieved) {
		this.isArchived = isArchieved;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public List<User> getAlUser() {
		return alUser;
	}

	public void setAlUser(List<User> alUser) {
		this.alUser = alUser;
	}
	
	public Set<Labels> getAlLabels() {
		return alLabels;
	}

	public void setAlLabels(Set<Labels> alLabels) {
		this.alLabels = alLabels;
	}
	

	/*public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}*/
	
	
	

}
