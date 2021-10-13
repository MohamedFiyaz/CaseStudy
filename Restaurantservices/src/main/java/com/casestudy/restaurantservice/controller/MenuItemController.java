package com.casestudy.restaurantservice.controller;

import com.casestudy.restaurantservice.dto.MenuItemRequestDto;
import com.casestudy.restaurantservice.entity.MenuItem;
import com.casestudy.restaurantservice.exception.ItemNotFoundException;
import com.casestudy.restaurantservice.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/restaurant/menu/")
@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("add")
    public ResponseEntity<String> addMenuItemsToRestaurant(@RequestBody MenuItemRequestDto menuItemRequestDto) throws ItemNotFoundException {
        menuItemService.saveMenuItem(menuItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Added");
    }

    @GetMapping("item/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) throws ItemNotFoundException {
        MenuItem menuItem = menuItemService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(menuItem);
    }
}
