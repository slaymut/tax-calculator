package com.odealim.tax_calculator.model;

public enum Country {
  US, CANADA, FRANCE;

  public static Country fromString(String value) {
    for (Country country : Country.values()) {
      if (country.name().equalsIgnoreCase(value)) {
        return country;
      }
    }
    throw new IllegalArgumentException("The country does not exist: " + value);
  }
  
}
