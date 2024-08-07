package com.odealim.tax_calculator.service.strategy;

import java.math.BigDecimal;

import com.odealim.tax_calculator.model.Product;

public interface TaxStrategy {
  BigDecimal calculateTax(Product product);
}
