package org.assignment.inventoryofreservedproducts.Repository;

import org.assignment.inventoryofreservedproducts.Entity.ItemReservedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReservedEventRepository extends JpaRepository<ItemReservedEvent,Long> {
}
