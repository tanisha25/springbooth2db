package com.demo.app.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * 
 * @author tanisha
 *
 */
@Entity
@Table(name ="reviews")
public class Reviews implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4412616725562144880L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    private Movie movie;
    private boolean critic = Boolean.FALSE;
	private double rate;
	private Long createdTimestamp;
	private Long updatedTimestamp;
	
	public Reviews() {
		
	}
	public Reviews(User user, Movie movie, double rate) {
		this.user = user;
		this.movie = movie;
		this.rate = rate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public boolean isCritic() {
		return critic;
	}
	public void setCritic(boolean critic) {
		this.critic = critic;
	}
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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

	@PrePersist
	public void prePersist()
	{
		if(id == null)
		{
			id = UUID.randomUUID();
		}
	}
}