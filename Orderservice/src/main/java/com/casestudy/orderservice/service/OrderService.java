package com.casestudy.orderservice.service;

import com.casestudy.orderservice.dto.*;
import com.casestudy.orderservice.entity.Order;
import com.casestudy.orderservice.entity.OrderedItem;
import com.casestudy.orderservice.entity.Payment;
import com.casestudy.orderservice.exception.OrderServiceException;
import com.casestudy.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedItemService orderedItemService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    RestOperations restTemplate;

    private static final String ENDPOINT_URL = "http://RESTAURANT-SERVICE/restaurant/menu/item/";

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws OrderServiceException {

        double totalPrice = 0;
        Order order = new Order(orderRequestDto.getOrderId(), orderRequestDto.getCustomerId(),
                orderRequestDto.getRestaurantId(), "Created", totalPrice);

        Order savedOrder = orderRepository.save(order);
        List<OrderedItemsDto> itemsOrdered = orderRequestDto.getItems();
        for(OrderedItemsDto itemsDto : itemsOrdered) {

            MenuItemDto menuItem = restTemplate.getForObject(ENDPOINT_URL + itemsDto.getItemId(), MenuItemDto.class);

            totalPrice = totalPrice + (menuItem.getPrice() * itemsDto.getQuantity());

            if(itemsDto.getQuantity() <= 0) {
                orderRepository.delete(order);
                throw new OrderServiceException("Quantity of the item cant be 0");
            }

            OrderedItem orderedItem = new OrderedItem(0L, menuItem.getName(), itemsDto.getQuantity(),
                    menuItem.getPrice(), menuItem.getMenuItemId(), savedOrder);

            orderedItemService.saveItem(orderedItem);
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        Payment payment = orderRequestDto.getPayment();
        payment.setOrderId(orderRequestDto.getOrderId());
        payment.setAmount(totalPrice);
        payment.setTransactionId(UUID.randomUUID().toString());

        if(paymentProcessing().equalsIgnoreCase("Success")){
            payment.setPaymentStatus(paymentProcessing());
            payment.setOrder(order);
            payment.setCustomerId(orderRequestDto.getCustomerId());
            paymentService.savePayment(payment);
        } else {
            throw new OrderServiceException("Payment Failed , Something wrong in Payment ApI");
        }
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrder(order);
        orderResponseDto.setPayment(payment);
        return orderResponseDto;
    }

    public String paymentProcessing() {
        return new Random().nextBoolean()? "Success" : "Failure";
    }

    public boolean cancelOrder(Long orderId) throws OrderServiceException {
        Order order = orderRepository.findByOrderId(orderId);
        if(order != null) {
            if(order.getStatus().equalsIgnoreCase("Cancelled")) {
                throw new OrderServiceException("This Order is already Cancelled");
            }
            order.setStatus("Cancelled");
            orderRepository.save(order);
            return true;
        }
        else {
            throw new OrderServiceException("No records available for the specified id");
        }
    }

    public OrderResponseDto getOrderById(Long orderId) throws OrderServiceException {
        Order order =  orderRepository.findByOrderId(orderId);
        if(order != null){
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrder(order);
            Payment payment = paymentService.getPaymentByOrderId(orderId);
            orderResponseDto.setPayment(payment);
            List<OrderedItem> itemsOrdered = orderedItemService.findByOrderId(orderId);
            orderResponseDto.setItems(itemsOrdered);
            return orderResponseDto;
        } else {
            throw new OrderServiceException("No records available for the specified id");
        }
    }

    public BillAmountDto getOrderAmountByOrderId(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        BillAmountDto billAmountDto = new BillAmountDto();
        if(order != null) {
            List<OrderedItem> itemsOrdered = orderedItemService.findByOrderId(orderId);
            double totalPrice = 0;
            List<ItemsDto> itemsDtoList = new LinkedList<>();
            for(OrderedItem items : itemsOrdered) {
                totalPrice = totalPrice + (items.getPrice()*items.getQuantity());
                ItemsDto itemsDto = new ItemsDto(items.getName(), items.getQuantity(), items.getPrice());
                itemsDtoList.add(itemsDto);
            }
            billAmountDto.setItems(itemsDtoList);
            billAmountDto.setTotalAmount(totalPrice);
        }
        return billAmountDto;
    }
}
