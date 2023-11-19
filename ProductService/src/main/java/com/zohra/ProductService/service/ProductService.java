package com.zohra.ProductService.service;

import com.zohra.Core.dto.request.ProductRequest;
import com.zohra.Core.dto.response.ProductResponse;

public interface ProductService {
  long addProduct(ProductRequest productRequest);

  ProductResponse getProductByID(long id);

  ProductResponse updateProduct(Long productId, ProductRequest productRequest);

  void deleteProduct(Long productId);

  void reduceQuantity(Long productId, Long quantity);
}
