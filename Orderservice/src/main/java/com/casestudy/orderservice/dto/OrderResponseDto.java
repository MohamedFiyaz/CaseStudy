package com.casestudy.orderservice.dto;

import com.casestudy.orderservice.entity.Order;
import com.casestudy.orderservice.entity.OrderedItem;
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
public class OrderResponseDto {

    private Order order;
    private Payment payment;
    private List<OrderedItem> items;
}
