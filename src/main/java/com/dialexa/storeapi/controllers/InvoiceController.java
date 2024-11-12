package com.dialexa.storeapi.controllers;

import com.dialexa.storeapi.entities.InvoiceEntity;
import com.dialexa.storeapi.services.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "Invoice Endpoints")
@RequestMapping(path = "/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    @Operation(summary = "Get all invoices")
    public List<InvoiceEntity> getAllInvoices() {
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get invoice by id")
    public ResponseEntity<InvoiceEntity> getInvoiceById(@PathVariable UUID id) {
        Optional<InvoiceEntity> invoice = invoiceService.findById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create invoice")
    public InvoiceEntity createInvoice(@RequestBody InvoiceEntity invoice) {
        return invoiceService.save(invoice);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update invoice")
    public ResponseEntity<InvoiceEntity> updateInvoice(@PathVariable UUID id, @RequestBody InvoiceEntity invoice) {
        invoice.setId(id);
        InvoiceEntity updatedInvoice = invoiceService.save(invoice);
        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete invoice")
    public ResponseEntity<Void> deleteInvoice(@PathVariable UUID id) {
        invoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}