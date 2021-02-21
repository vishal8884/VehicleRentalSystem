package com.vishal.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.vehicle.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	User findByName(String name);
}
