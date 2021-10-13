package com.casestudy.restaurantservice.respository;

import com.casestudy.restaurantservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByName(String name);

    List<Restaurant> findByLocation(String location);

    List<Restaurant> findByDistance(double distance);

    List<Restaurant> findByCuisine(String cuisine);

    List<Restaurant> findByBudget(int budget);
}
