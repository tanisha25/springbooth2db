package com.demo.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.model.Movie;

/**
 * 
 * @author tanisha
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID>{
 
	@Query("SELECT m FROM Movie m WHERE m.name = ?1")
	Movie findByName(String name);
	
	@Transactional
	@Query("SELECT m.name FROM Movie m INNER JOIN Reviews r ON m.id = r.movie.id LEFT JOIN User u ON r.user.id = u.id AND u.critic = FALSE AND u.expert = FALSE AND u.admin = FALSE WHERE m.releasedYear = :releasedYear GROUP BY m.name ORDER BY sum(r.rate) desc")
	List<String> findTopNMoviesByViewersAndReleasedYear(@Param("releasedYear") int releasedYear, Pageable page);

	@Transactional
	@Query("SELECT m.name FROM Movie m INNER JOIN Reviews r ON r.critic = TRUE AND m.id = r.movie.id LEFT JOIN User u ON r.user.id = u.id AND u.critic = TRUE WHERE m.releasedYear = :releasedYear GROUP BY m.name ORDER BY sum(r.rate) desc")
	List<String> findTopNMoviesByCriticsAndReleasedYear(@Param("releasedYear") int releasedYear, Pageable page);

	@Transactional
	@Query(value = "SELECT m.name FROM Movie m INNER JOIN Reviews r ON m.id = r.movie_id  LEFT JOIN User u ON r.user_id = u.id AND u.critic = FALSE AND u.expert = FALSE AND u.admin = FALSE  WHERE LOWER(m.genres) regexp LOWER(:genre) GROUP BY m.name ORDER BY sum(r.rate) desc", nativeQuery = true)
	List<String> findTopNMoviesByViewersAndGenre(@Param("genre") String genre, Pageable page);
	
	@Transactional
	@Query(value = "SELECT m.name FROM Movie m INNER JOIN Reviews r ON r.critic = TRUE AND m.id = r.movie_id LEFT JOIN User u ON r.user_id = u.id AND u.critic = TRUE WHERE LOWER(m.genres) regexp LOWER(:genre) GROUP BY m.name ORDER BY sum(r.rate) desc", nativeQuery = true)
	List<String> findTopNMoviesByCriticsAndGenre(@Param("genre") String genre, Pageable page);
}
