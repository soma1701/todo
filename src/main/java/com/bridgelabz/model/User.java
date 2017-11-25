package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user", schema="soma")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "mygen")
	@GenericGenerator(name = "mygen", strategy = "native")
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "mobNo")
	private String mobNo;

	@Column(name = "isValid")
	private boolean isValid;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private String profileImage; 

	@ManyToMany(mappedBy = "alUser")
	@JsonIgnore
	private List<Notes> alNotes = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Labels> alLabels;

	public List<Labels> getLabels() {
		return alLabels;
	}

	public void setLabels(List<Labels> alLabels) {
		this.alLabels = alLabels;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public List<Notes> getNotes() {
		return alNotes;
	}

	public void setNotes(List<Notes> notes) {
		this.alNotes = notes;
	}

	public String getImage() {
		return profileImage;
	}

	public void setImage(String image) {
		this.profileImage = image;
	}

	public List<Notes> getAlNotes() {
		return alNotes;
	}

	public void setAlNotes(List<Notes> alNotes) {
		this.alNotes = alNotes;
	}

	public List<Labels> getAlLabels() {
		return alLabels;
	}

	public void setAlLabels(List<Labels> alLabels) {
		this.alLabels = alLabels;
	}
	

}
