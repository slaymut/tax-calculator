package com.odealim.tax_calculator.service.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.odealim.tax_calculator.model.Product;

@Component("us")
public class UsTaxStrategy implements TaxStrategy {
  private static final BigDecimal US_TAX_RATE = new BigDecimal(0.10);

  @Override
  public void calculateTax(Product product) {
    BigDecimal taxedPrice = product.getPrice().multiply(US_TAX_RATE);
    product.setPrice(taxedPrice);
  }

  
}
