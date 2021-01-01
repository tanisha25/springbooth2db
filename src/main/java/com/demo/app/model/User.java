package com.demo.app.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * 
 * @author tanisha
 *
 */
@Entity
@Table(name ="user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3549627000498998104L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private UUID id;
	private String name;
	private boolean critic = Boolean.FALSE;
	private boolean expert = Boolean.FALSE;
	private boolean admin = Boolean.FALSE;
	private Long createdTimestamp;
	private Long updatedTimestamp;
	
	public User() {
		
	}
	public User(String name) {
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCritic() {
		return critic;
	}
	public void setCritic(boolean critic) {
		this.critic = critic;
	}
	public Long getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Long createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Long getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public void setUpdatedTimestamp(Long updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	
	public boolean isExpert() {
		return expert;
	}
	public void setExpert(boolean expert) {
		this.expert = expert;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@PrePersist
	public void prePersist()
	{
		if(id == null)
		{
			id = UUID.randomUUID();
		}
	}
}