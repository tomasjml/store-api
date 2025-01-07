package com.dialexa.storeapi.utils;

import com.dialexa.storeapi.entities.InvoiceEntity;
import com.dialexa.storeapi.entities.ProductEntity;
import com.dialexa.storeapi.entities.enums.ProductCategoryEnum;
import com.dialexa.storeapi.entities.records.InvoiceRecord;
import com.dialexa.storeapi.entities.records.ProductRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MockUtil {
    public static InvoiceEntity getInvoiceEntity(UUID id) {
        return InvoiceEntity.builder()
                .id(id)
                .date(LocalDate.now())
                .totalAmount(BigDecimal.valueOf(1000))
                .build();
    }

    public static ProductEntity getProductEntity(UUID id) {
        return ProductEntity.builder()
                .id(id)
                .name("Product 1")
                .price(BigDecimal.valueOf(100))
                .category(ProductCategoryEnum.ELECTRONICS)
                .build();
    }

    public static ProductRecord getProductRecord(UUID id) {
        return MappingUtil.mapProductEntityToRecord(getProductEntity(id));
    }

    public static InvoiceRecord getInvoiceRecord(UUID id) {
        return MappingUtil.mapInvoiceEntityToRecord(getInvoiceEntity(id));
    }
}
