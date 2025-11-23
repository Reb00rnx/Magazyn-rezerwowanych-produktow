package org.assignment.inventoryofreservedproducts.Repository;

import org.assignment.inventoryofreservedproducts.Entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem,String> {


    Optional<InventoryItem> findBySku(String sku);

    @Modifying
    @Query("UPDATE InventoryItem i SET i.available = i.available - :qty, " +
            "i.reserved = i.reserved + :qty, i.version = i.version + 1 " +
            "WHERE i.sku = :sku AND i.available >= :qty AND i.version = :expectedVersion")
    int attemptReserve(
        @Param("sku") String sku,
        @Param("qty") int qty,
        @Param("expectedVersion") Long expectedVersion
    );
}