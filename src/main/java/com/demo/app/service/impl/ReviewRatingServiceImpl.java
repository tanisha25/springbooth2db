package com.demo.app.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.app.model.Movie;
import com.demo.app.model.Reviews;
import com.demo.app.model.User;
import com.demo.app.repository.MovieRepository;
import com.demo.app.repository.ReviewsRepository;
import com.demo.app.repository.UserRepository;
import com.demo.app.service.ReviewRatingService;
import com.demo.app.utils.Constants;
import com.demo.app.utils.Utils;


@Service("reviewRatingService")
public class ReviewRatingServiceImpl implements ReviewRatingService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ReviewsRepository reviewsRepository;
	
	Logger log = LoggerFactory.getLogger(ReviewRatingServiceImpl.class);

	@Override
	public void addUsers(String userName) {
		User user = new User(userName);
		user.setCreatedTimestamp(Utils.getCurrentTimeInUTC());
		user.setUpdatedTimestamp(Utils.getCurrentTimeInUTC());
		userRepository.save(user);
	}
	
	@Override
	public void getListOfTopNMoviesByViewersAndReleasedYear(int noOfMovies, int releasedYear)
	{
		Pageable page = PageRequest.of(0, noOfMovies);
		List<String> movies = movieRepository.findTopNMoviesByViewersAndReleasedYear(releasedYear, page);
		if(!Utils.isEmpty(movies))
			System.out.println("Get List of Top "+ noOfMovies + " By Viewer Score in the year " + releasedYear );
		else 
			System.out.println(Constants.NO_MOVIE_FOUND);
		movies.stream().forEach(movie -> System.out.println(movie));
	}
	
	@Override
	public void getListOfTopNMoviesByCriticsAndReleasedYear(int noOfMovies, int releasedYear)
	{
		Pageable page = PageRequest.of(0, noOfMovies);
		List<String> movies = movieRepository.findTopNMoviesByCriticsAndReleasedYear(releasedYear, page);
		if(!Utils.isEmpty(movies))
			System.out.println("Get List of Top "+ noOfMovies + " By Critics Score in " + releasedYear );
		else 
			System.out.println(Constants.NO_MOVIE_FOUND);
		movies.stream().forEach(movie -> System.out.println(movie));
	}
	
	@Override
	public void getListOfTopNMoviesByCriticsAndGenre(int noOfMovies, String genre)
	{
		Pageable page = PageRequest.of(0, noOfMovies);
		List<String> movies = movieRepository.findTopNMoviesByCriticsAndGenre(genre, page);
		if(!Utils.isEmpty(movies))
			System.out.println("Get List of Top "+ noOfMovies + " By Critics Score in " + genre );
		else 
			System.out.println(Constants.NO_MOVIE_FOUND);
		movies.stream().forEach(movie -> System.out.println(movie));
	}
	
	@Override
	public void getListOfTopNMoviesByViewersAndGenre(int noOfMovies, String genre)
	{
		Pageable page = PageRequest.of(0, noOfMovies);
		List<String> movies = movieRepository.findTopNMoviesByViewersAndGenre(genre, page);
		if(!Utils.isEmpty(movies))
			System.out.println("Get List of Top "+ noOfMovies + " By Viewer Score in " + genre );
		else 
			System.out.println(Constants.NO_MOVIE_FOUND);
		movies.stream().forEach(movie -> System.out.println(movie));
	}
	
	@Override
	public void addReview(String userName, String movieName, double rate) throws Exception
	{
		if(rate<= 1.0 || rate >= 10.0)
			throw new Exception(Constants.REVIEW_SCORE_RANGE_ERROR);
		User user = userRepository.findByName(userName);
		if(Utils.isEmpty(user))
			throw new Exception("User "+ userName + " does not exist");
		Movie movie = movieRepository.findByName(movieName);
		if(Utils.isEmpty(movie))
			throw new Exception("Movie "+ movieName + " does not exist");
		if(movie.getReleasedYear() > Utils.getCurrentYear(Utils.getCurrentTimeInUTC()))
			throw new Exception(Constants.MOVIE_NOT_RELEASED);
		if(!Utils.isEmpty(reviewsRepository.findByUserIdAndMovieId(user.getId(), movie.getId())))
			throw new Exception(Constants.MULTIPLE_REVIEW);
		Reviews review = new Reviews(user, movie, rate);
		review.setCreatedTimestamp(Utils.getCurrentTimeInUTC());
		review.setUpdatedTimestamp(Utils.getCurrentTimeInUTC());
		reviewsRepository.save(review);
		int reviewsCount = reviewsRepository.countReviewsByUserId(user.getId());
		if(reviewsCount >= Constants.CRITIC_REVIEW_THRESHOLD_COUNT)
		{
			user.setCritic(true);
			if(review.getRate() >= Constants.CRITIC_THRESHOLD_RATE) {
				changInMovieRate(review);
				user.setExpert(true);
			}
			user.setUpdatedTimestamp(Utils.getCurrentTimeInUTC());
			userRepository.save(user);
		}
	}
	
	public void changInMovieRate(Reviews review) {
		review.setRate(Constants.CRITIC_RATE_INCREASE * review.getRate());
		review.setCritic(true);/*Submitted by critic*/
		review.setUpdatedTimestamp(Utils.getCurrentTimeInUTC());
		reviewsRepository.save(review);
	}
	
	@Override
	public void getAverageReviewScoreInReleasedYear(int releasedYear)
	{
		Double averageScore = reviewsRepository.findAverageReviewScoreInReleasedYear(releasedYear);
		if(!Utils.isEmpty(averageScore))
			System.out.println("Average Review Score in the year "+ releasedYear + " is "+ averageScore);
		else
		 System.out.println(Constants.MOVIE_NOT_RELEASED_YEAR);
	}
	
	@Override
	public void getAverageReviewScoreInGenre(String genre)
	{
		log.info("Get Average Review Score in Genre");
		Double averageScore = reviewsRepository.findAverageReviewScoreInGenre(genre);
		if(!Utils.isEmpty(averageScore))
			System.out.println("Average Review Score in the genre "+ genre + " is "+ averageScore);
		else
			System.out.println(Constants.MOVIE_GENRE_NOT_RELEASED);
	}
	
	@Override
	public void getAverageReviewScoreInMovie(String movieName)
	{
		Double averageScore = reviewsRepository.findAverageReviewScoreInMovie(movieName);
		if(!Utils.isEmpty(averageScore))
			System.out.println("Average Review Score in the movie "+ movieName + " is "+ averageScore);
		else
			System.out.println("No movie with "+ movieName + "exist");
		
	}
	
	@Override
	public void addMovie(String movieName, int releasedYear, String genres) {
		Movie movie = new Movie(movieName, releasedYear, genres.trim());
		movie.setCreatedTimestamp(Utils.getCurrentTimeInUTC());
		movie.setUpdatedTimestamp(Utils.getCurrentTimeInUTC());
		movieRepository.save(movie);
	}

	
}
