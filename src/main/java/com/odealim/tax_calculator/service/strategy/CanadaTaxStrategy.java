package com.odealim.tax_calculator.service.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.odealim.tax_calculator.model.Product;

@Component("CANADA")
public class CanadaTaxStrategy implements TaxStrategy {
  private static final BigDecimal CANADA_TAX_RATE = new BigDecimal(0.05);

  @Override
  public BigDecimal calculateTax(Product product) {
    BigDecimal taxedPrice = product.getPrice().multiply(CANADA_TAX_RATE);
    return product.getPrice().add(taxedPrice).setScale(2, RoundingMode.HALF_UP);
  }
}
