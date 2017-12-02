package com.bridgelabz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="note_user")
public class NoteUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne
    @JoinColumn(name = "note_id")

	private Notes note;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "id")
	private User user;
	
	@Column(name="is_owner")
	private boolean isOwner;

	@JsonIgnore
	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	
}
