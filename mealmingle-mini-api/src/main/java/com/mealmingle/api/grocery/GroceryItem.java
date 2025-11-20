package com.mealmingle.api.grocery;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ingredientName;

    private Double quantity;
    private String unit;

    private boolean purchased = false;
}