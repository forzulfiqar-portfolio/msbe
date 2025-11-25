package com.example.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InventoryItem {
    @Id
    private String productId;
    private int availableQty;
}