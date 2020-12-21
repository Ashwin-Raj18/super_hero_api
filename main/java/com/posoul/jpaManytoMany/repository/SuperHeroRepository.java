package com.posoul.jpaManytoMany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posoul.jpaManytoMany.entities.SuperHero;


public interface SuperHeroRepository extends JpaRepository<SuperHero, Integer> {

	public Optional<SuperHero> findByHeroName(String heroname);

}
