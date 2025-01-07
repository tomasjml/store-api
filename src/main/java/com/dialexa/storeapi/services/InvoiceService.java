package com.dialexa.storeapi.services;

import com.dialexa.storeapi.entities.InvoiceEntity;
import com.dialexa.storeapi.entities.records.InvoiceRecord;
import com.dialexa.storeapi.entities.records.ProductRecord;
import com.dialexa.storeapi.repositories.InvoiceRepository;
import com.dialexa.storeapi.utils.MappingUtil;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;

    public InvoiceService(InvoiceRepository invoiceRepository, ProductService productService) {
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
    }

    public List<InvoiceRecord> findAll() {
        return invoiceRepository.findAll().stream()
                .map(MappingUtil::mapInvoiceEntityToRecord)
                .collect(Collectors.toList());
    }

    public InvoiceRecord findById(UUID id) {
        Optional<InvoiceEntity> invoice = invoiceRepository.findById(id);
        if (invoice.isEmpty()) {
            throw new EntityNotFoundException("Invoice not found for id: " + id);
        }
        InvoiceEntity invoiceEntity = invoice.get();
        List<ProductRecord> invoiceProducts = productService.findAllProductsByInvoiceId(invoiceEntity.getId());
        return MappingUtil.mapInvoiceEntityToRecord(invoiceEntity, invoiceProducts);
    }

    public InvoiceEntity save(InvoiceEntity invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteById(UUID id) {
        invoiceRepository.deleteById(id);
    }
}
