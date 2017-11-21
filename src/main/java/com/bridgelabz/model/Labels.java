package com.bridgelabz.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="labels", schema="soma")
public class Labels {
	@Id  
	 @GeneratedValue(strategy=GenerationType.AUTO, generator="mygen")
	 @GenericGenerator(name="mygen",strategy="native")
	 @Column(name = "label_id")  
	 private int labelId;
	
	@Column(name="label_name")
	private String labelName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
//	@ManyToOne
	@ManyToMany(mappedBy="alLabels", cascade = CascadeType.ALL)
//	@JoinColumn(name="notes_id")
	@JsonIgnore
	private List<Notes> alNote;

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Notes> getAlNote() {
		return alNote;
	}

	public void setAlNote(List<Notes> alNote) {
		this.alNote = alNote;
	}

	public void setCreatedTime(Date date) {
		// TODO Auto-generated method stub
		
	}

}
