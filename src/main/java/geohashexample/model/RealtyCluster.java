package geohashexample.model;

import lombok.Value;

@Value
public class RealtyCluster {

  private int cityId;
  private double latitude;
  private double longitude;
  private String geohashPrefix;
  private long quantity;
  private double averagePrice;
}
