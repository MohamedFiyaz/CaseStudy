package com.casestudy.orderservice.repository;

import com.casestudy.orderservice.entity.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {

	List<OrderedItem> findByOrderOrderId(Long orderId);

	void deleteByOrderedItemId(Long orderedItemId);

}
