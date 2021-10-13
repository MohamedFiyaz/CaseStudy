package com.casestudy.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequestDto {
    private String name;
    private String location;
    private double distance;
    private String cuisine;
    private int budget;
    private String openingTime;
    private String closingTime;
}
