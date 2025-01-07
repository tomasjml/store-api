package com.dialexa.storeapi.repositories;

import com.dialexa.storeapi.entities.ProductEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query(
            "SELECT p FROM ProductEntity p JOIN InvoiceProductEntity ip ON p.id = ip.id.productId WHERE ip.id.invoiceId = :invoiceId")
    List<ProductEntity> findProductsByInvoiceId(@Param("invoiceId") UUID invoiceId);
}
