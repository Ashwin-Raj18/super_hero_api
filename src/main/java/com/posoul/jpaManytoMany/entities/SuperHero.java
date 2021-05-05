package com.posoul.jpaManytoMany.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//OWNER ENTITY
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SuperHero {
	@GeneratedValue
	@Id
	@Column(unique = true)
	private int heroId;
	
	@Column(unique = true)
	private String heroName;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)// by default it is eager
	@JoinTable(
			  name = "SuperHero_movies", 
			  joinColumns = @JoinColumn(name = "hero_id"), 
			  inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> movies = new ArrayList<Movie>();

	public SuperHero(String heroName) {
		super();
		this.heroName = heroName;
	}

}
