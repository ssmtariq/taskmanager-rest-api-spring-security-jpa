package com.ssmtariq.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssmtariq.taskmanager.model.User;

/**
 * @author SSM Tariq
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}
