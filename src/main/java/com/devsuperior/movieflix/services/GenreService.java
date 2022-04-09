package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;

@Service
public class GenreService {
	@Autowired
	private GenreRepository repo;

	public List<GenreDTO> getGenres() {
		return repo.findAll().stream().map(x-> new GenreDTO(x)).collect(Collectors.toList());
	}


}
