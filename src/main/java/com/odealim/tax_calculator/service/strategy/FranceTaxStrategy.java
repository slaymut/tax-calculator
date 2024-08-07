package com.odealim.tax_calculator.service.strategy;

import java.math.BigDecimal;

import com.odealim.tax_calculator.model.Product;

public class FranceTaxStrategy implements TaxStrategy {
  private static final BigDecimal FRANCE_TAX_RATE = new BigDecimal(0.20);

  @Override
  public void calculateTax(Product product) {
    BigDecimal taxedPrice = product.getPrice().multiply(FRANCE_TAX_RATE);
    product.setPrice(taxedPrice);
  }
}
