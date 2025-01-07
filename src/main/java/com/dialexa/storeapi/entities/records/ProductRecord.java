package com.dialexa.storeapi.entities.records;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecord(UUID id, String name, String category, BigDecimal price) {}
