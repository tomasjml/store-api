package com.dialexa.storeapi.services;

import com.dialexa.storeapi.entities.InvoiceEntity;
import com.dialexa.storeapi.repositories.InvoiceRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceEntity> findAll() {
        return invoiceRepository.findAll();
    }

    public Optional<InvoiceEntity> findById(UUID id) {
        return invoiceRepository.findById(id);
    }

    public InvoiceEntity save(InvoiceEntity invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteById(UUID id) {
        invoiceRepository.deleteById(id);
    }
}
