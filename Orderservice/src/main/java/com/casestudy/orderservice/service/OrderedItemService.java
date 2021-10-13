package com.casestudy.orderservice.service;

import com.casestudy.orderservice.entity.OrderedItem;
import com.casestudy.orderservice.repository.OrderedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedItemService {

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public OrderedItem saveOrderedItem(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);
    }

    public List<OrderedItem> findByOrderId(Long orderId) {
        return orderedItemRepository.findByOrderOrderId(orderId);
    }

    public OrderedItem saveItem(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);
    }

    public void deleteItemsById(Long itemId) {
        orderedItemRepository.deleteByOrderedItemId(itemId);
    }
}
