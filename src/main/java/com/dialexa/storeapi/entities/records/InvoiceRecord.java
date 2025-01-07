package com.dialexa.storeapi.entities.records;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InvoiceRecord(UUID id, LocalDate date, BigDecimal totalAmount, List<ProductRecord> products) {}
