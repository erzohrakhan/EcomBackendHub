package com.zohra.OrderService.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order_detail")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name="quantity")
    private Long quantity;
    @Column(name="amount")
    private Long amount;
    @Column(name="order_status")
    private String orderStatus;

    public Order(Long orderId, Long productId, Long quantity, Long amount, String orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
