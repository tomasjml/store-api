package com.dialexa.storeapi.entities.records;

import java.util.List;

public record ErrorResponseRecord(String message, List<String> details) {}
