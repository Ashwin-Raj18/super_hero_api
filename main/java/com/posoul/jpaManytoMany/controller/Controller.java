package com.posoul.jpaManytoMany.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.posoul.jpaManytoMany.dto.MovieRequest;
import com.posoul.jpaManytoMany.dto.MovieResponse;
import com.posoul.jpaManytoMany.dto.SuperHeroRequest;
import com.posoul.jpaManytoMany.dto.SuperHeroResponse;
import com.posoul.jpaManytoMany.service.MovieService;

@RestController
public class Controller {
	
	@Autowired
    MovieService movieService;	
    
	@PostMapping(path = "/addMovieWithHeros")
	public ResponseEntity<SuperHeroResponse> addMovieWithHeros(@RequestBody MovieRequest movieRequest) {
		SuperHeroResponse superHeroResponse = movieService.saveMovies(movieRequest);
		return new ResponseEntity<SuperHeroResponse>(
		          superHeroResponse, 
		          HttpStatus.OK);
	}
	

	@SuppressWarnings("null")
	@PostMapping("/removeHeros")
	public ResponseEntity<SuperHeroResponse> removeSupeFromMovie(@RequestBody SuperHeroRequest superHeroReq){
		SuperHeroResponse superHeroResponse =  movieService.removeSupeFromMove(superHeroReq);
		if(superHeroResponse ==  null) {
			List<String> l1 = new ArrayList<String>();
			l1.add("not removed");
			superHeroResponse.setResultSupe(l1);
			return new ResponseEntity<SuperHeroResponse>(
			          superHeroResponse, 
			          HttpStatus.NOT_MODIFIED);
		}else {
			return new ResponseEntity<SuperHeroResponse>(
			          superHeroResponse, 
			          HttpStatus.OK);
		}
	}
	@PostMapping("/removeMovie/{movieId}")
	public ResponseEntity<String> removeMovie(@PathVariable int movieId){
		String res = movieService.removeMovie(movieId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@PostMapping("/getMovie/{movieName}")
	public ResponseEntity<SuperHeroResponse> getMovie(@PathVariable String movieName){
		SuperHeroResponse res = movieService.findMovie(movieName);
		return new ResponseEntity<SuperHeroResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/getAllMovieInfo")
	public ResponseEntity<List<SuperHeroResponse>> getAllMovieInfo(){
		List<SuperHeroResponse> res = movieService.findAllMovies();
		return new ResponseEntity<List<SuperHeroResponse>>(res, HttpStatus.OK);
	}
	
	@PostMapping("/getMovies/{heroName}")
	public ResponseEntity<MovieResponse> getMoviesOfHero(@PathVariable String heroName){
		MovieResponse res = movieService.findMoviesByHero(heroName);
		return new ResponseEntity<MovieResponse>(res, HttpStatus.OK);
	}
	
}
