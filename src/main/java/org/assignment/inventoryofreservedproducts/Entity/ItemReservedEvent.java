// ItemReservedEvent.java (POPRYWNA WERSJA JAKO KLASA)
package org.assignment.inventoryofreservedproducts.Entity;

import jakarta.persistence.*;
import org.assignment.inventoryofreservedproducts.Enum.Type;

import java.time.LocalDateTime;

@Entity
public class ItemReservedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String payload;
    private LocalDateTime createdAt;


    public ItemReservedEvent(String sku, Type type, String payload, LocalDateTime createdAt) {
        this.sku = sku;
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public ItemReservedEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}