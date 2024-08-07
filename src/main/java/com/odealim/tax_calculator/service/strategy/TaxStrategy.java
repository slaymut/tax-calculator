package com.odealim.tax_calculator.service.strategy;

import com.odealim.tax_calculator.model.Product;

public interface TaxStrategy {
  void calculateTax(Product product);
}
