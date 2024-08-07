package com.odealim.tax_calculator.service.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.odealim.tax_calculator.model.Product;

@Component("US")
public class UsTaxStrategy implements TaxStrategy {
  private static final BigDecimal US_TAX_RATE = new BigDecimal(0.10);

  @Override
  public BigDecimal calculateTax(Product product) {
    BigDecimal taxedPrice = product.getPrice().multiply(US_TAX_RATE);
    return product.getPrice().add(taxedPrice).setScale(2, RoundingMode.HALF_UP);
  }

  
}
