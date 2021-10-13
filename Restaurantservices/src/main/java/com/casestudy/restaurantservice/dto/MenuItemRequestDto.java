package com.casestudy.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemRequestDto {

    private Long menuItemId;
    private String name;
    private String description;
    private int quantity;
    private int price;
}
