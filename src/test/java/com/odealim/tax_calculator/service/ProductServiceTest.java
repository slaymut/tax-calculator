package com.odealim.tax_calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.odealim.tax_calculator.model.Country;
import com.odealim.tax_calculator.model.Product;

public class ProductServiceTest {
  
  @Mock
  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnListOfProducts_getAllProducts() {
    Product product1 = new Product(1L, "Product 1", BigDecimal.valueOf(100), Country.US);
    Product product2 = new Product(2L, "Product 2", BigDecimal.valueOf(200), Country.CANADA);

    List<Product> expectedProducts = Arrays.asList(
      product1,
      product2
    );
    when(productService.getAllProducts()).thenReturn(expectedProducts);

    List<Product> actualProducts = productService.getAllProducts();

    assertEquals(expectedProducts, actualProducts);
    verify(productService).getAllProducts();
  }

  @Test
  void shouldReturnProductWhenProductExists_getProductById() throws Exception {
    Long id = 1L;
    Product expectedProduct = new Product(id, "Test Product", BigDecimal.valueOf(100), Country.US);
    when(productService.getProductById(id)).thenReturn(expectedProduct);

    Product response = productService.getProductById(id);

    assertEquals(expectedProduct, response);
    verify(productService).getProductById(id);
  }

  @Test
  void shouldThrowExceptionWhenProductDoesNotExist_getProductById() throws Exception {
    Long id = 1L;
    when(productService.getProductById(id)).thenThrow(new Exception("Product not found with id: " + id));

    assertThrows(Exception.class, () -> productService.getProductById(id));
    verify(productService).getProductById(id);
  }

  @Test
  void shouldReturnCreatedProductWhenValidProductProvided_createProduct() {
    Product inputProduct = new Product(null, "Dior", BigDecimal.valueOf(150.00), Country.FRANCE);
    Product createdProduct = new Product(1L, "Dior", BigDecimal.valueOf(150.00), Country.FRANCE);
    when(productService.createProduct(inputProduct)).thenReturn(createdProduct);

    Product response = productService.createProduct(inputProduct);

    assertEquals(createdProduct, response);
    verify(productService).createProduct(inputProduct);
  }
}
