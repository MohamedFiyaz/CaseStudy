package com.casestudy.restaurantservice.service;

import com.casestudy.restaurantservice.dto.FoodMenuResponseDto;
import com.casestudy.restaurantservice.dto.MenuItemRequestDto;
import com.casestudy.restaurantservice.dto.RestaurantRequestDto;
import com.casestudy.restaurantservice.dto.RestaurantResponseDto;
import com.casestudy.restaurantservice.entity.FoodMenu;
import com.casestudy.restaurantservice.entity.MenuItem;
import com.casestudy.restaurantservice.entity.Restaurant;
import com.casestudy.restaurantservice.exception.RestaurantSearchServiceException;
import com.casestudy.restaurantservice.respository.RestaurantRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodMenuService foodMenuService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant saveRestaurant(RestaurantRequestDto restaurantRequestDto){
        val restaurant = new Restaurant(0L, restaurantRequestDto.getName(), restaurantRequestDto.getLocation(), restaurantRequestDto.getDistance(),
                restaurantRequestDto.getCuisine(), restaurantRequestDto.getBudget());
        val savedRestaurant = restaurantRepository.save(restaurant);
        FoodMenu foodMenu = new FoodMenu(0L, restaurantRequestDto.getOpeningTime(), restaurantRequestDto.getClosingTime(), savedRestaurant);
        foodMenuService.saveMenu(foodMenu);
        return savedRestaurant;
    }

    public RestaurantResponseDto getRestaurantByName(String name) throws RestaurantSearchServiceException {
        val restaurants = restaurantRepository.findByName(name);
        if (restaurants.isEmpty())
            throw new RestaurantSearchServiceException("No Restaurants found by this name " + name);
        return new RestaurantResponseDto(restaurants);
    }

    public RestaurantResponseDto getRestaurantByLocation(String location) throws RestaurantSearchServiceException{
        val restaurants = restaurantRepository.findByLocation(location);
        if (restaurants.isEmpty())
            throw new RestaurantSearchServiceException("No Restaurants found in this location " + location);
        return new RestaurantResponseDto(restaurants);
    }

    public RestaurantResponseDto getRestaurantByDistance(double distance) throws RestaurantSearchServiceException {
        val restaurants = restaurantRepository.findByDistance(distance);
        if (restaurants.isEmpty())
            throw new RestaurantSearchServiceException("No Restaurants found in this distance" + distance + " range");
        return new RestaurantResponseDto(restaurants);
    }

    public RestaurantResponseDto getRestaurantByCuisine(String cuisine) throws RestaurantSearchServiceException {
        val restaurants = restaurantRepository.findByCuisine(cuisine);
        if (restaurants.isEmpty())
            throw new RestaurantSearchServiceException("No Restaurants found in this cuisine" + cuisine);
        return new RestaurantResponseDto(restaurants);
    }

    public RestaurantResponseDto getRestaurantByBudget(int budget) throws RestaurantSearchServiceException {
        val restaurants = restaurantRepository.findByBudget(budget);
        if (restaurants.isEmpty())
            throw new RestaurantSearchServiceException("No Restaurants found in this budget" + budget + " range");
        return new RestaurantResponseDto(restaurants);
    }

    public FoodMenuResponseDto findMenuItemByRestaurantId(Long restaurantId) throws RestaurantSearchServiceException {
        FoodMenu foodMenu = foodMenuService.getMenuByRestaurantId(restaurantId);
        if (foodMenu == null)
            throw new RestaurantSearchServiceException("No such restaurant found");

        List<MenuItem> menuItems = menuItemService.findByFoodMenuId(foodMenu.getFoodMenuId());

        if (menuItems.isEmpty())
            throw new RestaurantSearchServiceException("No Menu Item found for given restaurant");

        List<MenuItemRequestDto> menuItemRequestDtos = menuItems
                .stream()
                .map(it -> modelMapper.map(it, MenuItemRequestDto.class))
                .collect(Collectors.toList());
        return  new FoodMenuResponseDto(foodMenu.getOpeningTime(), foodMenu.getClosingTime(), menuItemRequestDtos);
    }
}
