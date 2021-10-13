package com.casestudy.restaurantservice.service;

import com.casestudy.restaurantservice.dto.MenuItemRequestDto;
import com.casestudy.restaurantservice.entity.FoodMenu;
import com.casestudy.restaurantservice.entity.MenuItem;
import com.casestudy.restaurantservice.exception.ItemNotFoundException;
import com.casestudy.restaurantservice.respository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    FoodMenuService foodMenuService;

    @Autowired
    MenuItemRepository menuItemRepository;

    public MenuItem saveMenuItem(MenuItemRequestDto menuItemRequestDto) throws ItemNotFoundException {
        Optional<FoodMenu> foodMenus = foodMenuService.getMenuById(menuItemRequestDto.getMenuItemId());
        if (foodMenus.isPresent()){
            MenuItem menuItem = new MenuItem(0L, menuItemRequestDto.getName(), menuItemRequestDto.getDescription(),
                    menuItemRequestDto.getQuantity(), menuItemRequestDto.getPrice(), foodMenus.get());
            return menuItemRepository.save(menuItem);
        }
        throw new ItemNotFoundException("FoodMenu not Found");
    }

    public List<MenuItem> findByFoodMenuId(Long id){
        return menuItemRepository.findByFoodMenuFoodMenuId(id);
    }

    public MenuItem findById(Long id) throws ItemNotFoundException {
        MenuItem menuItem = menuItemRepository.findByMenuItemId(id);
        if (menuItem == null){
            throw new ItemNotFoundException("No Menu Item found");
        }
        return menuItem;
    }
}
