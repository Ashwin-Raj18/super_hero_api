package com.posoul.jpaManytoMany.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieResponse {
	private String heroName;
	private List<String> movies;
}
