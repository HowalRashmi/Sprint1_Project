package com.springboot.lms.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.JWT.CustomUserDetails;
import com.springboot.lms.JWT.JwtUtil;
import com.springboot.lms.entities.IdPass;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private  DaoAuthenticationProvider aut;
	
	//object of user details
	@Autowired
	private CustomUserDetails custom;
	
	
	@Autowired
	private JwtUtil jwt;
	
	@PostMapping("/gettoken")
	public ResponseEntity<String> getToken(@RequestBody IdPass id)
	{
		try {
			this.aut.authenticate(new UsernamePasswordAuthenticationToken(id.getUsername(), id.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		UserDetails usr=this.custom.loadUserByUsername(id.getUsername());
		return ResponseEntity.ok(jwt.generateToken(usr));
	}
}










