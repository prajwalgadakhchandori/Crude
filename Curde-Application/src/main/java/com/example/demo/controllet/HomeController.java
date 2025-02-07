package com.example.demo.controllet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.userService;

@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
	private userService userService;

	@PostMapping("/user")
	public ResponseEntity<Boolean> saveUser(@RequestBody User user) {
		try {
			userService.save(user);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);

		}
	}

	@GetMapping("/user")
	public ResponseEntity<Map<String, User>> getUser(@RequestParam("id") long id) {
	    try {
	        User user = userService.getById(id);
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	        Map<String, User> response = new HashMap<>();
	        response.put("user", new User(user)); 

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

}
