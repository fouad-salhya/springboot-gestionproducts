package com.products.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedbacks")
public class FeedbackEntity implements Serializable {
	
	private static final long serialVersionUID = 2405088702293405082L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long id;
	
	@Column
	private String feedbackId;
	
	@Column
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFeedback_id() {
		return feedbackId;
	}

	public void setFeedback_id(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	

}
