package org.assignment.inventoryofreservedproducts.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InventoryItem {

    @Id
    private String sku;
    private int available;
    private int reserved;
    private long version;

    public InventoryItem() {
    }

    public InventoryItem(String sku,
                         int available,
                         int reserved,
                         long version) {
        this.sku = sku;
        this.available = available;
        this.reserved = reserved;
        this.version = version;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
