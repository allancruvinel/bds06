package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	@Autowired
	private MovieRepository repo;
	@Autowired
	private ReviewRepository repoReview;

	public MovieDTO findById(Long id) {
		Movie movie = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		MovieDTO dto = new MovieDTO(movie);
		return dto;	
	}

	public Page<MovieDTO> findByGenre(Long genreId,Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.find(genreId,pageable).map(x-> new MovieDTO(x));
	}

	public List<ReviewDTO> getMovieReviewsById(Long id) {
		List<Review> reviews = repoReview.findByMovieId(id);
		return reviews.stream().map(x->new ReviewDTO(x)).collect(Collectors.toList());
	}
	
}
