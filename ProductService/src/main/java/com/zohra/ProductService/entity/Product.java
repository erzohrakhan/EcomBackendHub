package com.zohra.ProductService.entity;

import jakarta.persistence.*;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id", nullable = false)
  Long productId;

  @Column(name = "product_name", nullable = false, length = 100)
  String productName;

  @Column(name = "price", nullable = false)
  Long price;

  @Column(name = "quantity", nullable = false)
  Long quantity;

  public Product() {}

  public Product(String productName, Long price, Long quantity) {
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
