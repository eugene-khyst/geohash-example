package geohashexample.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import geohashexample.model.RealtyCluster;
import geohashexample.model.RealtyPriceStatistics;
import java.util.List;
import java.util.Map;
import lombok.Value;

@Value
public class RealtyClusterResponse {

  @JsonProperty("stats")
  private final Map<Integer, RealtyPriceStatistics> priceStatistics;

  private final List<RealtyCluster> realty;
}
