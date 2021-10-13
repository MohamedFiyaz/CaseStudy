package com.casestudy.orderservice.dto;

import com.casestudy.orderservice.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

	private Long customerId;
	private Long restaurantId;
	private Long orderId;
	private List<OrderedItemsDto> items;
	private Payment payment;
}
