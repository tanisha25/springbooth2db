package com.demo.app.service.impl;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.demo.app.service.ReviewRatingService;
import com.demo.app.utils.Constants;
import com.demo.app.utils.Utils;

@Component
public class ReviewRatingApplicationRunner implements ApplicationRunner{

	@Autowired
	private ReviewRatingService service;
	
	Logger log = LoggerFactory.getLogger(ReviewRatingApplicationRunner.class);
	public void run(ApplicationArguments args) throws Exception {
		log.info("Review Rating Service starts");
		
		/**methods for adding features **/
		
		addUsersInSystem();
	
		addMoviesInSystem();
		
		addReviewsInSystem();
	
		getTopNMoviesByViewersAndReleasedYear();
		
		getTopNMoviesByViewersAndGenre();
		
		getTopNMoviesByCriticsAndReleasedYear();
		
		getTopNMoviesByCriticsAndGenre();
		
		getAvgReviewScoreInMovie();
		
		getAvgReviewScoreInGenre();
		
		getAvgReviewScoreInReleasedYear();
		
	}

	private void getAvgReviewScoreInReleasedYear() {
		Scanner s = new Scanner(System.in);
		System.out.println("Average Review Score In Particular Year");
		System.out.println("Input Released Year");
		int releasedYear = s.nextInt();
		service.getAverageReviewScoreInReleasedYear(releasedYear);
	}

	private void getAvgReviewScoreInGenre() {
		Scanner s = new Scanner(System.in);
		System.out.println("Average Review Score In Particular Movie");
		System.out.println("Input Movie");
		String movie = s.nextLine();
		service.getAverageReviewScoreInMovie(movie);
	}

	private void getAvgReviewScoreInMovie() {
		Scanner s = new Scanner(System.in);
		System.out.println("Average Review Score In Particular Genre");
		System.out.println("Input Genre");
		String genre = s.nextLine();
		service.getAverageReviewScoreInGenre(genre);
	}

	private void getTopNMoviesByCriticsAndGenre() {
		Scanner s = new Scanner(System.in);
		System.out.println("Top N Movies By Critics And Genre");
		System.out.println("Input N no");
		int no = s.nextInt();
		System.out.println("Genre");
		s.nextLine();
		String genre = s.nextLine();
		service.getListOfTopNMoviesByCriticsAndGenre(no, genre);
	}

	private void getTopNMoviesByCriticsAndReleasedYear() {
		Scanner s = new Scanner(System.in);
		System.out.println("Top N Movies By Critics And Released Year");
		System.out.println("Input N no");
		int num = s.nextInt();
		System.out.println("Released Year");
		int releasedYear = s.nextInt();
		service.getListOfTopNMoviesByCriticsAndReleasedYear(num, releasedYear);
	}

	private void getTopNMoviesByViewersAndGenre() throws Exception{
		Scanner s = new Scanner(System.in);
		System.out.println("Top N Movies By Viewers And Genre");
		System.out.println("Input N no");
		int num = s.nextInt();
		System.out.println("Genre");
		s.nextLine();
		String genre = s.nextLine();
		if(Utils.isEmpty(genre))
			throw new Exception(Constants.NO_GENRE_FOUND);
		service.getListOfTopNMoviesByViewersAndGenre(num, genre);
	}

	private void getTopNMoviesByViewersAndReleasedYear() {
		Scanner s = new Scanner(System.in);
		System.out.println("Top N Movies By Viewers And Released Year");
		System.out.println("Input N no");
		int num = s.nextInt();
		System.out.println("Released Year");
		int releasedYear = s.nextInt();
		service.getListOfTopNMoviesByViewersAndReleasedYear(num, releasedYear);
	}

	public void addUsersInSystem()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Add User Feature");
		System.out.println("No of Users");
		int noOfUsers = s.nextInt();
		s.nextLine();
		for(int i = 1; i<= noOfUsers; i++) {
			System.out.println("Add User "+ i);
			String user = s.nextLine();
		    service.addUsers(user);
		}
	}
	
	public void addMoviesInSystem()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Add Movies");
		System.out.println("Input the No Of Movies");
		int noOfMovies = s.nextInt();
		s.nextLine();
		for(int i = 0; i< noOfMovies; i++) {
			System.out.println("Input the Movie Name");
			String movie = s.nextLine();
			System.out.println("Input the released Year");
			int releasedYear = s.nextInt();
			System.out.println("Input its genres separated by commas");
			s.nextLine();
			String genres = s.nextLine();
			service.addMovie(movie, releasedYear, genres);
		}
	}
	
	public void addReviewsInSystem() throws Exception
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Add Reviews");
		System.out.println("Input No Of Reviews");
		int noOfReviews = s.nextInt();
		s.nextLine();
		for(int i = 0; i< noOfReviews; i++)
		{
			System.out.println("Input the User Name");
			String user = s.nextLine();
			System.out.println("Input the Movie Name");
			String movie = s.nextLine();
			System.out.println("Input the rating");
			double rate = s.nextDouble();
			service.addReview(user, movie, rate);
			s.nextLine();
		}
		
	}
}	
