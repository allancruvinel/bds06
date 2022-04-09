package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("email not found");
		}
		return user;
	}

	public UserDTO getUserProfile() {
		return new UserDTO(authService.authenticated());
	}

}
