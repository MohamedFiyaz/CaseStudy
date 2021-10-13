package com.casestudy.restaurantservice.respository;

import com.casestudy.restaurantservice.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {

    Optional<FoodMenu> findByFoodMenuId(Long menuId);

    FoodMenu findByRestaurantRestaurantId(Long restaurantId);
}
