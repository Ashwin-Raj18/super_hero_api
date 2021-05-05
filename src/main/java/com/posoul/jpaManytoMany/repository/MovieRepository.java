package com.posoul.jpaManytoMany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posoul.jpaManytoMany.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	public Optional<Movie> findByMovieName(String movieName);
}
