package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
//@PreAuthorize("hasAnyRole('MEMBER','VISITOR')")
public class MovieController {
	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	@GetMapping()
	public ResponseEntity<Page<MovieDTO>> findByGenre(@RequestParam(name = "genreId",defaultValue = "0") 
	Long genreId,@SortDefault(sort = "title",
	direction = Sort.Direction.ASC)Pageable pageable){
		return ResponseEntity.ok().body(service.findByGenre(genreId,pageable));
	}
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> getMovieReviewsById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.getMovieReviewsById(id));
	}
}
