package com.zohra.ProductService.controller;

import com.zohra.Core.dto.request.ProductRequest;
import com.zohra.Core.dto.response.ProductResponse;
import com.zohra.ProductService.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
  private static final Logger log = LogManager.getLogger(ProductController.class);

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductByID(@PathVariable("id") Long productId) {
    log.info("Get product for " + productId);
    return new ResponseEntity<>(productService.getProductByID(productId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Long> addProduct(
      @RequestBody @Valid ProductRequest productRequest, BindingResult bindingResult) {
    log.info(("Received request " + productRequest));
    if (bindingResult.hasErrors()) {
      throw new ValidationException(buildErrorMessage(bindingResult));
    }
    long productId = productService.addProduct(productRequest);
    return new ResponseEntity<>(productId, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(
      @PathVariable("id") Long productId,
      @RequestBody @Valid ProductRequest productRequest,
      BindingResult bindingResult) {
    log.info("update Product " + productId);
    if (bindingResult.hasErrors()) {
      throw new ValidationException(buildErrorMessage(bindingResult));
    }

    ProductResponse updatedProduct = productService.updateProduct(productId, productRequest);
    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
    log.info("delete product " + productId);
    productService.deleteProduct(productId);
    return ResponseEntity.ok("Product deleted successfully");
  }

  @PutMapping("/reduce-quantity/{id}")
  public ResponseEntity<String> reduceQuantity(
      @PathVariable("id") Long productId, @RequestParam Long quantity) {
    if (quantity <= 0) {
      return ResponseEntity.badRequest().body("Quantity cannot be negative or zero");
    }
    productService.reduceQuantity(productId, quantity);
    return ResponseEntity.ok("Quantity reduced successfully");
  }

  private String buildErrorMessage(BindingResult bindingResult) {
    StringBuilder errors = new StringBuilder();
    bindingResult
        .getFieldErrors()
        .forEach(
            error ->
                errors
                    .append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append("; "));
    return errors.toString();
  }
}
