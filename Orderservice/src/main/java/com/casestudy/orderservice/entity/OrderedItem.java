package com.casestudy.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ordered_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderedItemId;
    private String name;
    private int quantity;
    private double price;
    private Long itemId;

    @ManyToOne
    @JsonIgnore
    private Order order;

}
