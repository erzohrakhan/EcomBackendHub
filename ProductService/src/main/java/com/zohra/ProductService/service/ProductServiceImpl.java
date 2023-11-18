package com.zohra.ProductService.service;

import com.zohra.ProductService.entity.Product;
import com.zohra.ProductService.exception.ProductNotFoundException;
import com.zohra.ProductService.exception.ProductServiceException;
import com.zohra.ProductService.model.ProductRequest;
import com.zohra.ProductService.model.ProductResponse;
import com.zohra.ProductService.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
  private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

  private ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public long addProduct(ProductRequest productRequest) {
    log.info("Adding product");
    Product product =
        new Product(productRequest.name(), productRequest.price(), productRequest.quantity());
    productRepository.save(product);

    return product.getProductId();
  }

  @Override
  public ProductResponse getProductByID(long productId) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new ProductNotFoundException(
                        "product with given id not found", "PRODUCT_NOT_FOUND"));

    return new ProductResponse(
        product.getProductId(),
        product.getProductName(),
        product.getPrice(),
        product.getQuantity());
  }

  @Override
  public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new ProductNotFoundException(
                        "product with given id not found", "PRODUCT_NOT_FOUND"));

    product.setProductName(productRequest.name());
    product.setPrice(productRequest.price());
    product.setQuantity(productRequest.quantity());

    productRepository.save(product);
    return new ProductResponse(
        product.getProductId(),
        product.getProductName(),
        product.getPrice(),
        product.getQuantity());
  }

  @Override
  public void deleteProduct(Long productId) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new ProductNotFoundException(
                        "product with given id not found", "PRODUCT_NOT_FOUND"));
    productRepository.delete(product);
  }

  @Override
  public void reduceQuantity(Long productId, Long quantity) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new ProductNotFoundException(
                        "product with given id not found", "PRODUCT_NOT_FOUND"));

    if (product.getQuantity() < quantity) {
      throw new ProductServiceException(
          "product does not have sufficient quantity", "INSUFFICIENT_QUANTITY");
    }

    product.setQuantity(product.getQuantity() - quantity);
    productRepository.save(product);
  }
}
