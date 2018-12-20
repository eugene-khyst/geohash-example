package geohashexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RealtyCluster {

  private int cityId;

  @JsonProperty("lat")
  private double latitude;

  @JsonProperty("lng")
  private double longitude;

  @JsonProperty("geohash")
  private String geohashPrefix;

  @JsonProperty("qnt")
  private long quantity;

  @JsonProperty("avgPrice")
  private double averagePrice;
}
