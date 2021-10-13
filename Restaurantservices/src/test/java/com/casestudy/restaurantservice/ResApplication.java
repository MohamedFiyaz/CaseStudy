package com.casestudy.restaurantservice;

import com.casestudy.restaurantservice.dto.RestaurantRequestDto;
import com.casestudy.restaurantservice.dto.RestaurantResponseDto;
import com.casestudy.restaurantservice.entity.FoodMenu;
import com.casestudy.restaurantservice.entity.Restaurant;
import com.casestudy.restaurantservice.respository.RestaurantRepository;
import com.casestudy.restaurantservice.service.FoodMenuService;
import com.casestudy.restaurantservice.service.RestaurantService;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResApplication extends TestCase {

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantServiceImpl;

    @MockBean
    private FoodMenuService foodMenuService;

    public ResApplication(){
        super();
    }

    @Test
    public void suits() {
        new TestSuite(ResApplication.class);
    }

    @Test
    public void testSaveRestaurant() throws Exception {
        RestaurantRequestDto restaurantRequest = new RestaurantRequestDto();
        restaurantRequest.setName("Mannu Salwa");
        restaurantRequest.setLocation("Chennai");
        restaurantRequest.setDistance(10.0);
        restaurantRequest.setCuisine("Chinese Foods");
        restaurantRequest.setBudget(500);
        restaurantRequest.setOpeningTime("10:00 AM");
        restaurantRequest.setClosingTime("10:00 PM");
        Restaurant restaurant = new Restaurant(0L, restaurantRequest.getName(),
                restaurantRequest.getLocation(), restaurantRequest.getDistance(), restaurantRequest.getCuisine(), restaurantRequest.getBudget());
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        Restaurant savedRestaurant = restaurantServiceImpl.saveRestaurant(restaurantRequest);

        FoodMenu foodMenu = new FoodMenu(0L, restaurantRequest.getOpeningTime(), restaurantRequest.getClosingTime(), savedRestaurant);
        when(foodMenuService.saveMenu(any(FoodMenu.class))).thenReturn(foodMenu);

        assertNotNull(savedRestaurant);
        assertEquals("Mannu Salwa", savedRestaurant.getName());
        assertEquals("Chennai", savedRestaurant.getLocation());
        assertEquals(10.0, savedRestaurant.getDistance());
        assertEquals("Chinese Foods", savedRestaurant.getCuisine());
        assertEquals(500, savedRestaurant.getBudget());
    }

    @Test
    public void testFindByName() throws Exception {
        List<Restaurant> restaurantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        when(restaurantRepository.findByName(any(String.class))).thenReturn(restaurantsList);
        RestaurantResponseDto restaurantDto = restaurantServiceImpl.getRestaurantByName("West Food");
        assertEquals("West Food", restaurantDto.getRestaurants().get(0).getName());
    }

    @Test
    public void testFindByLocation() throws Exception {
        List<Restaurant> restaurantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        when(restaurantRepository.findByLocation(any(String.class))).thenReturn(restaurantsList);
        RestaurantResponseDto restaurantDto = restaurantServiceImpl.getRestaurantByLocation("Chennai");
        assertEquals("West Food", restaurantDto.getRestaurants().get(0).getName());
    }

    @Test
    public void testFindByDistance() throws Exception {
        List<Restaurant> restaurantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        when(restaurantRepository.findByDistance(any(Double.class))).thenReturn(restaurantsList);
        RestaurantResponseDto restaurantDto = restaurantServiceImpl.getRestaurantByDistance(10.0);
        assertEquals("West Food", restaurantDto.getRestaurants().get(0).getName());
    }

    @Test
    public void testFindByCuisine() throws Exception {
        List<Restaurant> restaurantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        when(restaurantRepository.findByCuisine(any(String.class))).thenReturn(restaurantsList);
        RestaurantResponseDto restaurantDto = restaurantServiceImpl.getRestaurantByCuisine("West India Foods");
        assertEquals("West Food", restaurantDto.getRestaurants().get(0).getName());
    }

    @Test
    public void testFindByBudget() throws Exception {
        List<Restaurant> restaurantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        when(restaurantRepository.findByBudget(any(Integer.class))).thenReturn(restaurantsList);
        RestaurantResponseDto restaurantDto = restaurantServiceImpl.getRestaurantByBudget(100);
        assertEquals("West Food", restaurantDto.getRestaurants().get(0).getName());
    }
}
