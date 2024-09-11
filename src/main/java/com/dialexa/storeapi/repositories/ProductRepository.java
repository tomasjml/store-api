package com.dialexa.storeapi.repositories;

import com.dialexa.storeapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
