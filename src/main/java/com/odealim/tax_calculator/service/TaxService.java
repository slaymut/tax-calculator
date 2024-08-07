package com.odealim.tax_calculator.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.odealim.tax_calculator.model.Country;
import com.odealim.tax_calculator.model.Product;
import com.odealim.tax_calculator.service.strategy.TaxStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaxService {
  private final Map<Country, TaxStrategy> taxStrategies;

  public BigDecimal calculateTax(Product product) {
    TaxStrategy taxStrategy = taxStrategies.get(product.getCountry());
    if (taxStrategy == null) {
      throw new IllegalArgumentException("The country does not exist: " + product.getCountry());
    }

    return taxStrategy.calculateTax(product);
  }
}
