package com.example.stock.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private Long quantity;

    public Stock() {
    }

    public Stock(Long id, Long quantity) {
        this(id, 0L, quantity);
    }

    public Stock(Long id, Long productId, Long quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void decrease(Long quantity) {
        if(this.quantity - quantity < 0) {
            throw new RuntimeException("재고는 0개미만이 될 수 없습니다.");
        }

        this.quantity -= quantity;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Stock stock = (Stock) other;
        return Objects.equals(id, stock.id) && Objects.equals(productId, stock.productId) && Objects.equals(getQuantity(), stock.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, getQuantity());
    }
}
