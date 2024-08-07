package com.odealim.tax_calculator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.odealim.tax_calculator.model.Product;
import com.odealim.tax_calculator.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) throws Exception {
    return productRepository.findById(id)
      .orElseThrow(() -> new Exception("Product not found with id: " + id));
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }
  
  public void deleteProduct(Long id) throws Exception {
    Product product = getProductById(id);
    productRepository.delete(product);
  }
}
