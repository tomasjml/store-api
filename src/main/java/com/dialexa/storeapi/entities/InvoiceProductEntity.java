package com.dialexa.storeapi.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "stacy_store_db", name = "invoice_product")
public class InvoiceProductEntity {
    @EmbeddedId
    private InvoiceProductId id;

    @Data
    @Embeddable
    public static class InvoiceProductId implements Serializable {
        @Column(name = "invoice_id")
        private UUID invoiceId;

        @Column(name = "product_id")
        private UUID productId;
    }
}
