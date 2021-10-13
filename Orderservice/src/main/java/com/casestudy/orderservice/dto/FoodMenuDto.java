package com.casestudy.orderservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodMenuDto {
	
	private Long id;
	private String activeFrom;
	private String activeTill;
	@JsonProperty("restaurant")
	private RestaurantDto restaurant;
}
