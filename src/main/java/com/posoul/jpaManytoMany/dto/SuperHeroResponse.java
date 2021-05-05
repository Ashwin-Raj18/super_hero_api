package com.posoul.jpaManytoMany.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperHeroResponse {
	private String movieName;
	private List<String> resultSupe;
}
