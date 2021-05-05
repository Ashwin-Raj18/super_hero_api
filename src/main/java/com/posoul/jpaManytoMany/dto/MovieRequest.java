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
public class MovieRequest {
   private String movieName;
   private List<String> superHeroNames;
   
}
