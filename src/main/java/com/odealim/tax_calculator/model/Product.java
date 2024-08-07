package com.odealim.tax_calculator.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Product {
  private Long id;
  private String name;
  private BigDecimal price;
  private Country country;

  public Product(String name, BigDecimal price, Country country) {
    this.name = name;
    this.price = price;
    this.country = country;
  }
}
