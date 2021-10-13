package com.casestudy.restaurantservice.controller;

import com.casestudy.restaurantservice.dto.FoodMenuResponseDto;
import com.casestudy.restaurantservice.dto.MenuItemRequestDto;
import com.casestudy.restaurantservice.dto.RestaurantRequestDto;
import com.casestudy.restaurantservice.dto.RestaurantResponseDto;
import com.casestudy.restaurantservice.entity.Restaurant;
import com.casestudy.restaurantservice.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class)
public class RestaurantControllerTestCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext context;

    @MockBean
    private RestaurantService restaurantService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAddRestaurant() throws Exception {
        RestaurantRequestDto restaurantRequest = new RestaurantRequestDto();
        restaurantRequest.setName("Mannu Salwa");
        restaurantRequest.setLocation("Chennai");
        restaurantRequest.setDistance(10.0);
        restaurantRequest.setCuisine("Chinese Foods");
        restaurantRequest.setBudget(500);
        restaurantRequest.setOpeningTime("10:00 AM");
        restaurantRequest.setClosingTime("10:00 PM");
        String jsonRequest = objectMapper.writeValueAsString(restaurantRequest);
        when(restaurantService.saveRestaurant(any(RestaurantRequestDto.class))).thenReturn(new Restaurant());
        RequestBuilder buildRequest = MockMvcRequestBuilders.post(
                        "/restaurant/addRestaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);
        mockMvc.perform(buildRequest).andExpect(status().isCreated()).andExpect(content().string("Restaurant Added Successfully")).andReturn();
    }

    @Test
    public void testGetRestaurantByName() throws Exception {
        List<Restaurant> resturantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
        when(restaurantService.getRestaurantByName(any(String.class))).thenReturn(restaurantResponse);

        RequestBuilder buildRequest = MockMvcRequestBuilders
                .get("/restaurant/name/Food")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetRestaurantByLocation() throws Exception {
        List<Restaurant> resturantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
        when(restaurantService.getRestaurantByLocation(any(String.class))).thenReturn(restaurantResponse);
        RequestBuilder buildRequest = MockMvcRequestBuilders
                .get("/restaurant/location/Chennai")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetRestaurantByDistance() throws Exception {
        List<Restaurant> resturantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
        when(restaurantService.getRestaurantByDistance(any(Double.class))).thenReturn(restaurantResponse);
        RequestBuilder buildRequest = MockMvcRequestBuilders
                .get("/restaurant/distance/10.0")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetRestaurantByCuisine() throws Exception {
        List<Restaurant> resturantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
        when(restaurantService.getRestaurantByCuisine(any(String.class))).thenReturn(restaurantResponse);
        RequestBuilder buildRequest = MockMvcRequestBuilders
                .get("/restaurant/cuisine/West India Foods")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testGetRestaurantByBudget() throws Exception {
        List<Restaurant> resturantsList = Arrays.asList(
                new Restaurant(0L, "West Food", "Chennai", 10, "West India Foods", 120),
                new Restaurant(0L, "East Food", "Madurai", 10, "East India Foods", 150)
        );
        RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
        when(restaurantService.getRestaurantByBudget(any(Integer.class))).thenReturn(restaurantResponse);
        RequestBuilder buildRequest = MockMvcRequestBuilders
                .get("/restaurant/budget/150")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testGetMenuItemsByRestaurantId() throws Exception {
        List<MenuItemRequestDto> menuItemsList = Arrays.asList(
                new MenuItemRequestDto(1L, "Dosa", "Menuitem for Chennai Restaurant", 1, 20),
                new MenuItemRequestDto(6L, "Idly", "Menuitem for Chennai Restaurant", 1, 15)
        );
        FoodMenuResponseDto foodMenu = new FoodMenuResponseDto("10:00 AM", "10:00 PM", menuItemsList);
        when(restaurantService.findMenuItemByRestaurantId(any(Long.class))).thenReturn(foodMenu);
        RequestBuilder buildRequest = MockMvcRequestBuilders
                .get("/restaurant/menuitem/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
    }
}
