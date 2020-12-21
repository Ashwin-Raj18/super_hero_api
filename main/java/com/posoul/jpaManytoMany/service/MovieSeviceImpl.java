package com.posoul.jpaManytoMany.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posoul.jpaManytoMany.dto.MovieRequest;
import com.posoul.jpaManytoMany.dto.MovieResponse;
import com.posoul.jpaManytoMany.dto.SuperHeroRequest;
import com.posoul.jpaManytoMany.dto.SuperHeroResponse;
import com.posoul.jpaManytoMany.entities.Movie;
import com.posoul.jpaManytoMany.entities.SuperHero;
import com.posoul.jpaManytoMany.repository.MovieRepository;
import com.posoul.jpaManytoMany.repository.SuperHeroRepository;

@Service
public class MovieSeviceImpl implements MovieService {

	@Autowired
	SuperHeroRepository superHeroRepository;
	@Autowired
	MovieRepository movieRepository;
	
	//add new movie with new and existing superhero or existing movie with added heros 
	public SuperHeroResponse saveMovies(MovieRequest movieRequest) {
		Movie movie = null;
		Optional<Movie> movieCheck = movieRepository.findByMovieName(movieRequest.getMovieName());
		if(movieCheck.isPresent()) {
			movie  = movieCheck.get() ;
		}else {
			movie = new Movie(movieRequest.getMovieName());
		}
		List<String> newHeroNames = new ArrayList<String>(movieRequest.getSuperHeroNames());
		 for(String supeName : movieRequest.getSuperHeroNames()){
			 Optional<SuperHero> superHeroEx = superHeroRepository.findByHeroName(supeName);//for checking existing hero list
			 if(superHeroEx.isPresent()){
				 SuperHero sup = superHeroEx.get();
				 newHeroNames.remove(sup.getHeroName());
				 movie.addSuperHero(sup);
			 }
		 }
		 for( String heroName : newHeroNames){
			 movie.addSuperHero( new SuperHero(heroName));
		 }
		 Movie movieRes = movieRepository.save(movie);
		 
		 SuperHeroResponse superHeroResponse = new SuperHeroResponse();
		 List<String> supeNames = new ArrayList<String>();
		 movieRes.getSuperHeros().forEach((hero)->{
			 supeNames.add(hero.getHeroName());
		 });
		 superHeroResponse.setResultSupe(supeNames);
		 superHeroResponse.setMovieName(movieRes.getMovieName());
		 return superHeroResponse;
	}
	
	
	public SuperHeroResponse removeSupeFromMove(SuperHeroRequest superHeroRequest) {
		Optional<Movie> movieOptional = movieRepository.findById(superHeroRequest.getId());
		SuperHeroResponse response = new SuperHeroResponse();
		movieOptional.ifPresent((m)->{					
			 	m.getSuperHeros().stream()
								 .filter(hero -> superHeroRequest.getSuperHeroNames().contains(hero.getHeroName()))
								 .collect(Collectors.toList())
								 .forEach(heroToremove ->m.removeSuperHero(heroToremove)); 
			 	
			movieRepository.save(m);
			List<String> supeNames = m.getSuperHeros().stream()
													  .map(h -> new String(h.getHeroName()))
													  .collect(Collectors.toList());
			response.setMovieName(m.getMovieName());
			response.setResultSupe(supeNames);
		  }
		);
		return response;
	}
	
	//delete movie and its connections only
	public String removeMovie(int movieId) {
		Optional<Movie> movie = movieRepository.findById(movieId);
		movie.ifPresent((m)->{
			List<SuperHero> supHeroList = new ArrayList<SuperHero>(m.getSuperHeros());
			supHeroList.forEach((superHeroObj)->{
				m.removeSuperHero(superHeroObj);
			});
			movieRepository.deleteById(movieId);
		});
		return "deleted";
	}
	
	public SuperHeroResponse findMovie(String movieName) {
		Optional<Movie> movie = movieRepository.findByMovieName(movieName);
		SuperHeroResponse heroRes = new SuperHeroResponse();
		List<String> supeNames = new ArrayList<String>();
		movie.ifPresent((m)->{
				m.getSuperHeros().forEach((h)->{
					supeNames.add(h.getHeroName());
				});
				heroRes.setResultSupe(supeNames);
				heroRes.setMovieName(m.getMovieName());
		});
		return heroRes;
	}
	
	public List<SuperHeroResponse> findAllMovies() {
		List<Movie> movie = movieRepository.findAll();
		List<SuperHeroResponse> heroRes = new ArrayList<SuperHeroResponse>();
		movie.forEach((m)->{
			SuperHeroResponse supe = new SuperHeroResponse();
			List<String> heroNames = new ArrayList<String>();
			supe.setMovieName(m.getMovieName());
			m.getSuperHeros().forEach((h)->{
				heroNames.add(h.getHeroName());
			});
			supe.setResultSupe(heroNames);
			heroRes.add(supe);
		});
		return heroRes;
	}
	
	//get all movie with requested hero
	public MovieResponse findMoviesByHero(String heroName) {
		Optional<SuperHero> supes = superHeroRepository.findByHeroName(heroName);
		MovieResponse res = new MovieResponse();
		List<String> movieNames = new ArrayList<String>();
		supes.ifPresent((supe)->{
			supe.getMovies().forEach((m)->{
				movieNames.add(m.getMovieName());
			});
			res.setHeroName(supe.getHeroName());
			res.setMovies(movieNames);
		});
		return res;
		
	}			
}
