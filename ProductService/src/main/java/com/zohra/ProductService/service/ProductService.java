package com.zohra.ProductService.service;

import com.zohra.ProductService.model.ProductRequest;
import com.zohra.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductByID(long id);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    void deleteProduct(Long productId);

    void reduceQuantity(Long productId, Long quantity);
}
