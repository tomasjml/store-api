package com.dialexa.storeapi.repositories;

import com.dialexa.storeapi.entities.InvoiceEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, UUID> {}
