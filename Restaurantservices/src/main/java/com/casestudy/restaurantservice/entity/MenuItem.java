package com.casestudy.restaurantservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "MENU_ITEM_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;
    private String name;
    private String description;
    private int quantity;
    private int price;
    @ManyToOne
    @JoinColumn(name = "food_menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FoodMenu foodMenu;
}
