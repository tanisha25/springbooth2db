package com.demo.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.app.model.User;

/**
 * 
 * @author tanisha
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	@Query("SELECT u FROM User u WHERE u.name = ?1")
	User findByName(String name);
}
