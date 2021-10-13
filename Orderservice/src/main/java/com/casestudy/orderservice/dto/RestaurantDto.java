package com.casestudy.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
	private Long id;
	private String name;
	private String location;
	private double distance;
	private String cuisine;
	private int budget;
}
