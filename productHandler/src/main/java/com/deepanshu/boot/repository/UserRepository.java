package com.deepanshu.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.boot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// method to get the user from name
	Optional<User> findByUserName(String userName);
}
