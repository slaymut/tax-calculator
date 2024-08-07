package com.odealim.tax_calculator.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.odealim.tax_calculator.model.Country;
import com.odealim.tax_calculator.model.Product;
import com.odealim.tax_calculator.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final TaxService taxService;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) throws Exception {
    return productRepository.findById(id)
      .orElseThrow(() -> new Exception("Product not found with id: " + id));
  }

  public Product createProduct(Product product) {
    if (product.getCountry() == null) {
      throw new IllegalArgumentException("Country cannot be null");
    }

    try {
      Country.fromString(product.getCountry().name());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid country: " + product.getCountry());
    }
    return productRepository.save(product);
  }
  
  public void deleteProduct(Long id) throws Exception {
    Product product = getProductById(id);
    productRepository.delete(product);
  }

  public BigDecimal calculateTax(Long id) throws Exception {
    Product product = getProductById(id);
    return taxService.calculateTax(product);
  }
}
