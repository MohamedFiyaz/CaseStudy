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
public class MenuItemDto {

	private Long menuItemId;
	private String name;
	private String description;
	private int quantity;
	private int price;
	@JsonProperty("menu")
	private FoodMenuDto menu;

}
