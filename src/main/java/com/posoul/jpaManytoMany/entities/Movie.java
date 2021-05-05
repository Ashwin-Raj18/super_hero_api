package com.posoul.jpaManytoMany.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {
	@GeneratedValue
	@Id
	@Column(unique = true)
	private int movieId;
	
	@Column(unique = true)
	private String movieName;
														// superheroes is owning side we dont want to remove movie if we delete a hero so remove is not included
	@ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private List<SuperHero> superHeros = new ArrayList<SuperHero>();
	
	public Movie(String movieName) {
		super();
		this.movieName = movieName;
	}
	
	//to add bidirectional mapping
	public void addSuperHero(SuperHero superHero) {
		this.superHeros.add(superHero);
		//to add this object to movies list
		//this is necessary for mapping
		superHero.getMovies().add(this);
	}
	
	//to remove biderectional mapping only
	public void removeSuperHero(SuperHero superHero) {
		this.superHeros.remove(superHero);
		//this is necessary for removing the mapping
		superHero.getMovies().remove(this);
	}


}
