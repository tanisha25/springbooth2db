package com.demo.app.service;

import java.util.List;

/**
 * 
 * @author tanisha
 *
 */
public interface ReviewRatingService {
	
	/**
	 * 
	 * @param userName
	 */
	public void addUsers(String userName);
	
	/**
	 * 
	 * @param userName
	 * @param movieName
	 * @param rate
	 */
	public void addReview(String userName, String movieName, double rate) throws Exception;
	
	/**
	 * 
	 * @param movieName
	 * @param releasedYear
	 * @param genres
	 */
	public void addMovie(String movieName, int releasedYear, String genres);
	
	/**
	 * 
	 * @param noOfMovies
	 * @param releasedYear
	 */
	public void getListOfTopNMoviesByViewersAndReleasedYear(int noOfMovies, int releasedYear);
	
	/**
	 * 
	 * @param noOfMovies
	 * @param genre
	 */
	public void getListOfTopNMoviesByViewersAndGenre(int noOfMovies, String genre);

	/**
	 * 
	 * @param noOfMovies
	 * @param releasedYear
	 */
	public void getListOfTopNMoviesByCriticsAndReleasedYear(int noOfMovies, int releasedYear);
	
	/**
	 * 
	 * @param noOfMovies
	 * @param genre
	 */
	public void getListOfTopNMoviesByCriticsAndGenre(int noOfMovies, String genre);

	/**
	 * 
	 * @param movieName
	 */
	public void getAverageReviewScoreInMovie(String movieName);
	
	/**
	 * 
	 * @param genre
	 */
	public void getAverageReviewScoreInGenre(String genre);
	
	/**
	 * 
	 * @param releasedYear
	 */
	public void getAverageReviewScoreInReleasedYear(int releasedYear);

}
