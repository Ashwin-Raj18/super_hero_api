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
public class SuperHeroRequest {
	private int id;
	private List<String> superHeroNames;
}
