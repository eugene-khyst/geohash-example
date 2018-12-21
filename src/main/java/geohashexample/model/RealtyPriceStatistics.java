package geohashexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RealtyPriceStatistics {

  @JsonIgnore
  private final int cityId;

  @JsonProperty("min")
  private final double minimum;

  @JsonProperty("max")
  private final double maximum;

  @JsonProperty("avg")
  private final double mean;

  @JsonProperty("sd")
  private final double standardDeviation;
}
