package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;

@Repository
public interface UserRepositary extends JpaRepository<User, Long> {
	
}
