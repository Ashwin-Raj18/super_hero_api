package com.posoul.jpaManytoMany.service;

import java.util.List;

import com.posoul.jpaManytoMany.dto.MovieRequest;
import com.posoul.jpaManytoMany.dto.MovieResponse;
import com.posoul.jpaManytoMany.dto.SuperHeroRequest;
import com.posoul.jpaManytoMany.dto.SuperHeroResponse;

public interface MovieService {
	
	public SuperHeroResponse saveMovies(MovieRequest movieRequest);
	
	public SuperHeroResponse removeSupeFromMove(SuperHeroRequest superHeroRequest);
	
	public String removeMovie(int movieId);
	
	public SuperHeroResponse findMovie(String movieName);
	
	public List<SuperHeroResponse> findAllMovies();
	
	public MovieResponse findMoviesByHero(String heroName);
}
