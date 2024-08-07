package com.odealim.tax_calculator.config;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.odealim.tax_calculator.model.Country;
import com.odealim.tax_calculator.service.strategy.CanadaTaxStrategy;
import com.odealim.tax_calculator.service.strategy.FranceTaxStrategy;
import com.odealim.tax_calculator.service.strategy.TaxStrategy;
import com.odealim.tax_calculator.service.strategy.UsTaxStrategy;

@Configuration
public class TaxStrategyConfig {
  @Bean
  public Map<Country, TaxStrategy> taxStrategies(
    UsTaxStrategy usTaxStrategy,
    CanadaTaxStrategy canadaTaxStrategy,
    FranceTaxStrategy franceTaxStrategy)
  {
    Map<Country, TaxStrategy> strategies = new EnumMap<>(Country.class);
    strategies.put(Country.US, usTaxStrategy);
    strategies.put(Country.CANADA, canadaTaxStrategy);
    strategies.put(Country.FRANCE, franceTaxStrategy);
    return strategies;
  }
}
