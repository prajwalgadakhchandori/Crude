package com.example.demo.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepositary;

@Service
public class userService {

	@Autowired
	private UserRepositary userRepositary;

	public void save(User user) {
		user.setPassword(hashPassword(user.getPassword()));
		userRepositary.save(user);

	}

	public User getById(long id) {
		return userRepositary.getById(id);
	}

	public static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}

	private static String bytesToHex(byte[] hash) {
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 64) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
}
