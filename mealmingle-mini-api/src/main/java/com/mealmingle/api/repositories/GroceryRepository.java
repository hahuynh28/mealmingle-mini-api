package com.mealmingle.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mealmingle.api.grocery.GroceryItem;

public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {

}
