package org.assignment.inventoryofreservedproducts.Dto;

import java.time.LocalDate;

public record InventoryItemResponseDto(String sku, int available, int reserved) {}
