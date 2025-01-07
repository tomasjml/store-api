package com.dialexa.storeapi.services;

import com.dialexa.storeapi.entities.ProductEntity;
import com.dialexa.storeapi.entities.records.ProductRecord;
import com.dialexa.storeapi.repositories.ProductRepository;
import com.dialexa.storeapi.utils.MappingUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductRecord> findAll() {
        return productRepository.findAll().stream()
                .map(MappingUtil::mapProductEntityToRecord)
                .collect(Collectors.toList());
    }

    public ProductRecord findById(UUID id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        return MappingUtil.mapProductEntityToRecord(productEntity.get());
    }

    public ProductRecord save(ProductRecord product) {
        return MappingUtil.mapProductEntityToRecord(productRepository.save(MappingUtil.mapProductRecordToEntity(product)));
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    public List<ProductRecord> findAllProductsByInvoiceId(UUID invoiceId) {
        return productRepository.findProductsByInvoiceId(invoiceId).stream()
                .map(MappingUtil::mapProductEntityToRecord)
                .collect(Collectors.toList());
    }
}
