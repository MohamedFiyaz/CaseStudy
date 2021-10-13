package com.casestudy.restaurantservice.respository;

import com.casestudy.restaurantservice.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByFoodMenuFoodMenuId(Long id);

    MenuItem findByMenuItemId(Long id);
}
