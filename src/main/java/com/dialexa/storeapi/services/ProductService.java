package com.dialexa.storeapi.services;

import com.dialexa.storeapi.entities.ProductEntity;
import com.dialexa.storeapi.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> findById(UUID id) {
        return productRepository.findById(id);
    }

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
