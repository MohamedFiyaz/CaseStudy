package com.casestudy.restaurantservice.dto;

import com.casestudy.restaurantservice.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDto {
    private List<Restaurant> restaurants;
}
