package com.barclays.helpqueue.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String description;
	
	@Column
	private LocalDateTime timeCreated;
	
	@Column
	private String status;
	
	@Column
	private String urgency;
	
	@Column
	private String solution;
	
	
	protected Ticket() {}
	
	public Ticket(String title, String author, String description, String status, String urgency, String solution) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.timeCreated = LocalDateTime.now();
		this.status = status;
		this.urgency = urgency;
		this.solution = solution;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public String getUrgency() {
		return urgency;
	}

	public Long getId() {
		return id;
	}

	public void setTimeCreated(LocalDateTime timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
}
