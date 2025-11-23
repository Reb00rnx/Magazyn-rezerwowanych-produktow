package org.assignment.inventoryofreservedproducts.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReservationRequestDto(
        @NotNull @Min(1) int qty) {
}
