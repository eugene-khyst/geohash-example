package geohashexample.model;

import lombok.Value;

@Value
public class RealtyPriceStatistics {

  private final int cityId;
  private final double quantity;
  private final double minimum;
  private final double maximum;
  private final double mean;
  private final double standardDeviation;
}
