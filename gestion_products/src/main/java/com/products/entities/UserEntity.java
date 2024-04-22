package com.products.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable  {

	private static final long serialVersionUID = -411114782216027212L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long id;
	
	@Column
	private String userId;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String telephone;
	
	@Column
	private String adresse;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<FeedbackEntity> feedbacks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return userId;
	}

	public void setUser_id(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<FeedbackEntity> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedbackEntity> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	

}
