package com.bvb.animalrescue.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/* @Query("SELECT u FROM Users u WHERE u.email=?1") */
	Optional<User> findByEmail(String email);

}
