package com.odealim.tax_calculator.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.odealim.tax_calculator.model.Country;
import com.odealim.tax_calculator.model.Product;
import com.odealim.tax_calculator.service.ProductService;

public class ProductControllerTest {
  
  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

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

    List<Product> actualProducts = productController.getAllProducts();

    assertEquals(expectedProducts, actualProducts);
    verify(productService).getAllProducts();
  }

  @Test
  void shouldReturnProductWhenProductExists_getProductById() throws Exception {
    Long id = 1L;
    Product expectedProduct = new Product(id, "Test Product", BigDecimal.valueOf(100), Country.US);
    when(productService.getProductById(id)).thenReturn(expectedProduct);

    ResponseEntity<Product> response = productController.getProductById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedProduct, response.getBody());
    verify(productService).getProductById(id);
  }

  @Test
  void shouldThrowExceptionWhenProductDoesNotExist_getProductById() throws Exception {
    Long id = 1L;
    when(productService.getProductById(id)).thenThrow(new Exception("Product not found with id: " + id));

    assertThrows(Exception.class, () -> productController.getProductById(id));
    verify(productService).getProductById(id);
  }

  @Test
  void shouldReturnCreatedProductWhenValidProductProvided_createProduct() {
    Product inputProduct = new Product(null, "Dior", BigDecimal.valueOf(150.00), Country.FRANCE);
    Product createdProduct = new Product(1L, "Dior", BigDecimal.valueOf(150.00), Country.FRANCE);
    when(productService.createProduct(inputProduct)).thenReturn(createdProduct);

    ResponseEntity<Product> response = productController.createProduct(inputProduct);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(createdProduct, response.getBody());
    verify(productService).createProduct(inputProduct);
  }

  @Test
  void shouldThrowExceptionWhenInvalidCountryProvided_createProduct() {
    Product product = new Product(null, "Dior", BigDecimal.valueOf(150.00), null);

    assertThrows(NullPointerException.class, () -> productController.createProduct(product));
    verify(productService, never()).createProduct(any());
  }

  @Test
  void shouldReturnProductPriceWithTaxWhenProductExists_calculateTax() throws Exception {
    Long id = 1L;
    Product product = new Product(id, "Dior", BigDecimal.valueOf(100.00), Country.FRANCE);
    BigDecimal expectedTax = BigDecimal.valueOf(120.00);
    when(productService.calculateTax(id)).thenReturn(expectedTax);

    productController.createProduct(product);
    ResponseEntity<BigDecimal> response = productController.calculateTax(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedTax, response.getBody());
    verify(productService).calculateTax(id);
  }

  @Test
  void shouldThrowExceptionWhenProductDoesNotExist_calculateTax() throws Exception {
    Long id = 1L;
    when(productService.calculateTax(id)).thenThrow(new Exception("Product not found with id: " + id));

    assertThrows(Exception.class, () -> productController.calculateTax(id));
    verify(productService).calculateTax(id);
  }

  @Test
  void shouldReturnSuccessMessageWhenProductDeleted_deleteProduct() throws Exception {
    Long id = 1L;
    doNothing().when(productService).deleteProduct(id);

    Product product = new Product(id, "Dior", BigDecimal.valueOf(100.00), Country.FRANCE);
    productController.createProduct(product);

    ResponseEntity<String> response = productController.deleteProduct(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("The product with id 1 has been deleted.", response.getBody());
    verify(productService).deleteProduct(id);
  }

  @Test
  void shouldThrowExceptionWhenProductDoesNotExist_deleteProduct() throws Exception {
    Long id = 1L;
    doThrow(new Exception("Product not found with id: " + id)).when(productService).deleteProduct(id);

    assertThrows(Exception.class, () -> productController.deleteProduct(id));
    verify(productService).deleteProduct(id);
  }
}
