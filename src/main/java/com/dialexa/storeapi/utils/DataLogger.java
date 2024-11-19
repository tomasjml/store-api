package com.dialexa.storeapi.utils;

import com.dialexa.storeapi.repositories.InvoiceRepository;
import com.dialexa.storeapi.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLogger implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLogger.class);

    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    public DataLogger(ProductRepository productRepository, InvoiceRepository invoiceRepository) {
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) {
        logger.info("DataLogger started");
        logger.info("Products: ");
        productRepository.findAll().forEach(product -> logger.info(product.getName()));
        logger.info("Invoices: ");
        invoiceRepository
                .findAll()
                .forEach(invoice -> logger.info(invoice.getId().toString()));
        logger.info("DataLogger finished");
    }
}
