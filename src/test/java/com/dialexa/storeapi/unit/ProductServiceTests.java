package com.dialexa.storeapi.unit;

import com.dialexa.storeapi.entities.ProductEntity;
import com.dialexa.storeapi.entities.records.ProductRecord;
import com.dialexa.storeapi.repositories.ProductRepository;
import com.dialexa.storeapi.services.ProductService;
import com.dialexa.storeapi.utils.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTests {
    @Mock
    private ProductRepository mockProductRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(mockProductRepository);
    }

    @Test
    void findAllProductRecords() {
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = MockUtil.getProductEntity(productId);
        ProductRecord productRecord = MockUtil.getProductRecord(productId);
        List<ProductEntity> productEntities = Collections.singletonList(productEntity);
        when(mockProductRepository.findAll()).thenReturn(productEntities);

        List<ProductRecord> actual = productService.findAll();

        assertEquals(Collections.singletonList(productRecord), actual);
        verify(mockProductRepository, times(1)).findAll();
    }

    @Test
    void findProductById() {
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = MockUtil.getProductEntity(productId);
        ProductRecord productRecord = MockUtil.getProductRecord(productId);
        when(mockProductRepository.findById(any(UUID.class))).thenReturn(Optional.of(productEntity));

        ProductRecord actual = productService.findById(productId);

        assertEquals(productRecord, actual);
        verify(mockProductRepository, times(1)).findById(productId);
    }

    @Test
    void findProductByIdNotFound() {
        UUID productId = UUID.randomUUID();
        when(mockProductRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        try {
            productService.findById(productId);
        } catch (Exception e) {
            assertEquals("Product with id " + productId + " not found", e.getMessage());
        }
    }

    @Test
    void saveProduct() {
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = MockUtil.getProductEntity(productId);
        ProductRecord productRecord = MockUtil.getProductRecord(productId);
        when(mockProductRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ProductRecord actual = productService.save(productRecord);

        assertEquals(productRecord, actual);
        verify(mockProductRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void deleteProduct() {
        UUID productId = UUID.randomUUID();
        productService.deleteById(productId);
        verify(mockProductRepository, times(1)).deleteById(productId);
    }

    @Test
    void findAllProductsByInvoiceId() {
        UUID invoiceId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = MockUtil.getProductEntity(productId);
        ProductRecord productRecord = MockUtil.getProductRecord(productId);
        List<ProductEntity> productEntities = Collections.singletonList(productEntity);
        when(mockProductRepository.findProductsByInvoiceId(invoiceId)).thenReturn(productEntities);

        List<ProductRecord> actual = productService.findAllProductsByInvoiceId(invoiceId);

        assertEquals(Collections.singletonList(productRecord), actual);
        verify(mockProductRepository, times(1)).findProductsByInvoiceId(invoiceId);
    }
}
