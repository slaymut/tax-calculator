package com.odealim.tax_calculator.service.strategy;

import java.math.BigDecimal;

import com.odealim.tax_calculator.model.Product;

public class CanadaTaxStrategy implements TaxStrategy {
  private static final BigDecimal CANADA_TAX_RATE = new BigDecimal(0.05);

  @Override
  public void calculateTax(Product product) {
    BigDecimal taxedPrice = product.getPrice().multiply(CANADA_TAX_RATE);
    product.setPrice(taxedPrice);
  }
}
