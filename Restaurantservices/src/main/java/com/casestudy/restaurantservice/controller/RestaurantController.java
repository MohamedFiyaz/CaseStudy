package com.casestudy.restaurantservice.controller;

import com.casestudy.restaurantservice.dto.FoodMenuResponseDto;
import com.casestudy.restaurantservice.dto.RestaurantRequestDto;
import com.casestudy.restaurantservice.dto.RestaurantResponseDto;
import com.casestudy.restaurantservice.exception.RestaurantSearchServiceException;
import com.casestudy.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/restaurant/")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("addRestaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody RestaurantRequestDto restaurant){
        restaurantService.saveRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant Added Successfully");
    }

    @GetMapping("name/{name}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByName(@PathVariable String name) throws RestaurantSearchServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantByName(name));
    }

    @GetMapping("location/{location}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByLocation(@PathVariable String location) throws RestaurantSearchServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantByLocation(location));
    }

    @GetMapping("distance/{distance}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByDistance(@PathVariable double distance) throws RestaurantSearchServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantByDistance(distance));
    }

    @GetMapping("cuisine/{cuisine}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByCuisine(@PathVariable String cuisine) throws RestaurantSearchServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantByCuisine(cuisine));
    }

    @GetMapping("budget/{budget}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByBudget(@PathVariable int budget) throws RestaurantSearchServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantByBudget(budget));
    }

    @GetMapping("menuitem/{id}")
    public ResponseEntity<FoodMenuResponseDto> getMenuItemsByRestaurantId(@PathVariable Long id) throws RestaurantSearchServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findMenuItemByRestaurantId(id));
    }
}
