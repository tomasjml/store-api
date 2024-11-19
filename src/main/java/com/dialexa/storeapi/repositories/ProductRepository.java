package com.dialexa.storeapi.repositories;

import com.dialexa.storeapi.entities.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {}
