package com.odealim.tax_calculator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odealim.tax_calculator.model.Product;
import com.odealim.tax_calculator.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
  
  private final ProductService productService;

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) throws Exception {
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }
  
  @PostMapping
  public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    Product productCreated = productService.createProduct(product);
    return ResponseEntity.ok(productCreated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {
    productService.deleteProduct(id);
    return ResponseEntity.ok("The product with id "+ id +" has been deleted.");
  }
}
