package org.assignment.inventoryofreservedproducts.Service;

import org.assignment.inventoryofreservedproducts.Repository.InventoryItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionHelperService {

    private final InventoryItemRepository inventoryItemRepository;

    public TransactionHelperService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int attemptUpdateInTransaction(String sku, int qty, long expectedVersion) {
        return inventoryItemRepository.attemptReserve(sku, qty, expectedVersion);
    }
}