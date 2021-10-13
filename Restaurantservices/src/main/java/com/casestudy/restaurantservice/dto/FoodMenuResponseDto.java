package com.casestudy.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodMenuResponseDto {

    private String openingTime;
    private String closingTime;
    private List<MenuItemRequestDto> menuItems;
}
