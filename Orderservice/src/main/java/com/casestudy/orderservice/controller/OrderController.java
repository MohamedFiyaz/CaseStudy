package com.casestudy.orderservice.controller;

import com.casestudy.orderservice.dto.BillAmountDto;
import com.casestudy.orderservice.dto.OrderRequestDto;
import com.casestudy.orderservice.dto.OrderResponseDto;
import com.casestudy.orderservice.exception.OrderServiceException;
import com.casestudy.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order/")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("placeOrder")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto order) throws OrderServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(order));
    }

    @PutMapping("cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) throws OrderServiceException{
        orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order Cancelled Successfully");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> viewOrder(@PathVariable Long orderId) throws OrderServiceException{
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(orderId));
    }

    @GetMapping("orderAmount/{orderId}")
    public ResponseEntity<BillAmountDto> getOrderAmountByOrderId(@PathVariable Long orderId){
        BillAmountDto billAmountDto = orderService.getOrderAmountByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(billAmountDto);
    }
}
