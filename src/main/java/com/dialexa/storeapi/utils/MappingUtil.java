package com.dialexa.storeapi.utils;

import com.dialexa.storeapi.entities.InvoiceEntity;
import com.dialexa.storeapi.entities.ProductEntity;
import com.dialexa.storeapi.entities.enums.ProductCategoryEnum;
import com.dialexa.storeapi.entities.records.InvoiceRecord;
import com.dialexa.storeapi.entities.records.ProductRecord;

import java.util.Collections;
import java.util.List;

public class MappingUtil {
    public static ProductRecord mapProductEntityToRecord(ProductEntity productEntity) {
        return new ProductRecord(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCategory().getValue(),
                productEntity.getPrice());
    }

    public static ProductEntity mapProductRecordToEntity(ProductRecord productRecord) {
        return ProductEntity.builder()
                .id(productRecord.id())
                .name(productRecord.name())
                .category(ProductCategoryEnum.valueOf(productRecord.category()))
                .price(productRecord.price())
                .build();
    }

    public static InvoiceRecord mapInvoiceEntityToRecord(InvoiceEntity invoiceEntity, List<ProductRecord> products) {
        return new InvoiceRecord(
                invoiceEntity.getId(), invoiceEntity.getDate(), invoiceEntity.getTotalAmount(), products);
    }

    public static InvoiceRecord mapInvoiceEntityToRecord(InvoiceEntity invoiceEntity) {
        return new InvoiceRecord(
                invoiceEntity.getId(), invoiceEntity.getDate(), invoiceEntity.getTotalAmount(), Collections.EMPTY_LIST);
    }
}
