package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service()
public class ReviewService {
	@Autowired
	private ReviewRepository repo;
	@Autowired
	private AuthService auth;

	public ReviewDTO insertReview(ReviewDTO dto) {
		User user = auth.authenticated();
		Movie mv = new Movie();
		mv.setId(dto.getMovieId());
		Review review = new Review();
		review.setMovie(mv);
		review.setText(dto.getText());
		review.setUser(user);
		dto = new ReviewDTO(repo.save(review));
		return dto;
	}
	
	
}
