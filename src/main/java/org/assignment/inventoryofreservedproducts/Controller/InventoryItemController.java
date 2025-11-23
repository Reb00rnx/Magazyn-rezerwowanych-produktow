package org.assignment.inventoryofreservedproducts.Controller;

import jakarta.validation.Valid;
import org.assignment.inventoryofreservedproducts.Dto.InventoryItemResponseDto;
import org.assignment.inventoryofreservedproducts.Dto.ReservationRequestDto;
import org.assignment.inventoryofreservedproducts.Service.InventoryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService){
        this.inventoryItemService = inventoryItemService;
    }

    @PostMapping("/inventory/{sku}/reserve")
    public ResponseEntity<InventoryItemResponseDto> reserve(@RequestBody @Valid ReservationRequestDto requestDto,
                                                            @PathVariable String sku){
        InventoryItemResponseDto response = inventoryItemService.reserve(requestDto.qty(),sku);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
