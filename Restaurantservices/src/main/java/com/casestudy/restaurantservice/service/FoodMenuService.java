package com.casestudy.restaurantservice.service;

import com.casestudy.restaurantservice.entity.FoodMenu;
import com.casestudy.restaurantservice.respository.FoodMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodMenuService {

    @Autowired
    private FoodMenuRepository repository;

    public FoodMenu saveMenu (FoodMenu menu){
        return repository.save(menu);
    }

    public Optional<FoodMenu> getMenuById (Long menuId){
        return repository.findByFoodMenuId(menuId);
    }

    public FoodMenu getMenuByRestaurantId(Long restaurantId){
        return repository.findByRestaurantRestaurantId(restaurantId);
    }
}
