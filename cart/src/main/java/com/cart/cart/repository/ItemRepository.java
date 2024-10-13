package com.cart.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.cart.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

