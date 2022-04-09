package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	@Autowired
	private UserRepository repo;
	
	public User authenticated() {
		try {
		return repo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		}catch(Exception ex) {
			throw new UnauthorizedException("Invalid user");
		}
	}
}
