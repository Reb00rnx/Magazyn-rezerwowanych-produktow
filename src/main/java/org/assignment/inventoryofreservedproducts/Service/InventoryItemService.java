package org.assignment.inventoryofreservedproducts.Service;

import org.assignment.inventoryofreservedproducts.Dto.InventoryItemResponseDto;
import org.assignment.inventoryofreservedproducts.Entity.InventoryItem;
import org.assignment.inventoryofreservedproducts.Entity.ItemReservedEvent;
import org.assignment.inventoryofreservedproducts.Enum.Type;
import org.assignment.inventoryofreservedproducts.Exception.VersionNotCorrectException;
import org.assignment.inventoryofreservedproducts.Repository.InventoryItemRepository;
import org.assignment.inventoryofreservedproducts.Repository.ItemReservedEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final ItemReservedEventRepository itemReservedEventRepository;
    private final TransactionHelperService transactionHelperService;

    private static final int MAX_ATTEMPT = 3;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository,
                                ItemReservedEventRepository itemReservedEventRepository,
                                TransactionHelperService transactionHelperService) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.itemReservedEventRepository = itemReservedEventRepository;
        this.transactionHelperService = transactionHelperService;
    }


    public InventoryItemResponseDto reserve(int qty, String sku) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        for (int attempt = 1; attempt <= MAX_ATTEMPT; attempt++) {

            InventoryItem item = findItemBySku(sku);
            long expectedVersion = item.getVersion();

            if (item.getAvailable() < qty) {
                throw new IllegalArgumentException("Not enough available in stock");
            }


            int updateCount = transactionHelperService.attemptUpdateInTransaction(sku, qty, expectedVersion);

            if (updateCount > 0) {
                String payload = "Items reserved: " + qty;
                ItemReservedEvent itemEvent = new ItemReservedEvent(sku, Type.RESERVED, payload, LocalDateTime.now());
                saveEvent(itemEvent);

                InventoryItem updatedItem = findItemBySku(sku);

                return new InventoryItemResponseDto(
                        updatedItem.getSku(), updatedItem.getAvailable(), updatedItem.getReserved()
                );

            } else {
                if (attempt == MAX_ATTEMPT) {
                    throw new VersionNotCorrectException("Failed to reserve item after " + MAX_ATTEMPT + " attempts");
                }
            }
        }
        throw new VersionNotCorrectException("Failed to reserve item due to unexpected error.");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void saveEvent(ItemReservedEvent itemEvent) {
        itemReservedEventRepository.save(itemEvent);
    }

    public InventoryItem findItemBySku(String sku) {
        return inventoryItemRepository.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("InventoryItem with SKU: " + sku + " not found"));
    }
}