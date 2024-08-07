package com.odealim.tax_calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.odealim.tax_calculator.model.Country;
import com.odealim.tax_calculator.model.Product;
import com.odealim.tax_calculator.service.strategy.TaxStrategy;

public class TaxServiceTest {
  
  @Mock
  private Map<Country, TaxStrategy> taxStrategies;

  @Mock
  private TaxStrategy usTaxStrategy;

  @Mock
  private TaxStrategy canadaTaxStrategy;

  @Mock
  private TaxStrategy franceTaxStrategy;

  @InjectMocks
  private TaxService taxService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    
    when(taxStrategies.get(Country.US)).thenReturn(usTaxStrategy);
    when(taxStrategies.get(Country.CANADA)).thenReturn(canadaTaxStrategy);
    when(taxStrategies.get(Country.FRANCE)).thenReturn(franceTaxStrategy);
  }
  
  @Test
  void shouldReturnCorrectProductPriceWithTaxForUSProduct_calculateTax() {
    Product product = new Product(1L, "Test Product", BigDecimal.valueOf(100), Country.US);
    BigDecimal expectedTax = BigDecimal.valueOf(10.00);
    when(usTaxStrategy.calculateTax(product)).thenReturn(expectedTax);

    BigDecimal actualTax = taxService.calculateTax(product);

    assertEquals(expectedTax, actualTax);
    verify(taxStrategies).get(Country.US);
    verify(usTaxStrategy).calculateTax(product);
  }

  @Test
  void shouldReturnCorrectProductPriceWithTaxForCanadaProduct_calculateTax() {
    Product product = new Product(1L, "Test Product", BigDecimal.valueOf(100), Country.CANADA);
    BigDecimal expectedTax = BigDecimal.valueOf(13.00);
    when(canadaTaxStrategy.calculateTax(product)).thenReturn(expectedTax);

    BigDecimal actualTax = taxService.calculateTax(product);

    assertEquals(expectedTax, actualTax);
    verify(taxStrategies).get(Country.CANADA);
    verify(canadaTaxStrategy).calculateTax(product);
  }

  @Test
  void shouldReturnCorrectProductPriceWithTaxForFranceProduct_calculateTax() {
    Product product = new Product(1L, "Test Product", BigDecimal.valueOf(100), Country.FRANCE);
    BigDecimal expectedTax = BigDecimal.valueOf(20.00);
    when(franceTaxStrategy.calculateTax(product)).thenReturn(expectedTax);

    BigDecimal actualTax = taxService.calculateTax(product);

    assertEquals(expectedTax, actualTax);
    verify(taxStrategies).get(Country.FRANCE);
    verify(franceTaxStrategy).calculateTax(product);
  }

  @Test
  void shouldThrowExceptionForUnsupportedCountry_calculateTax() {
    Product product = new Product(1L, "Test Product", BigDecimal.valueOf(100), null);

    assertThrows(IllegalArgumentException.class, () -> taxService.calculateTax(product));
  }
}
