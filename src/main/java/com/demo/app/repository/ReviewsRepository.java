package com.demo.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.app.model.Reviews;

/**
 * 
 * @author tanisha
 *
 */
@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, UUID>{
	
	@Query("SELECT r FROM Reviews r WHERE r.user.id = :userId AND r.movie.id = :movieId")
	Reviews findByUserIdAndMovieId(@Param("userId") UUID userId, @Param("movieId")UUID movieId);
	
	@Query("Select count(r) FROM Reviews r where r.user.id = :userId")
	int countReviewsByUserId(@Param("userId") UUID userId);

	@Query("Select avg(r.rate) FROM Reviews r INNER JOIN Movie m ON r.movie.id = m.id where m.releasedYear = :releasedYear")
	Double findAverageReviewScoreInReleasedYear(@Param("releasedYear") int releasedYear);

	@Query(value = "Select avg(r.rate) FROM Reviews r INNER JOIN Movie m ON r.movie_id = m.id where LOWER(m.genres) regexp LOWER(:genre)", nativeQuery = true)
	Double findAverageReviewScoreInGenre(@Param("genre") String genre);
	
	@Query(value = "Select avg(r.rate) FROM Reviews r INNER JOIN Movie m ON r.movie.id = m.id where m.name = :movieName")
	Double findAverageReviewScoreInMovie(@Param("movieName") String movieName);
}
