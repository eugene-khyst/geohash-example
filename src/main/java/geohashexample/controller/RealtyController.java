package geohashexample.controller;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import geohashexample.model.RealtyCluster;
import geohashexample.service.RealtyService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/realty")
public class RealtyController {

  private final RealtyService realtyService;

  @GetMapping
  public RealtyClusterResponse findRealtyClustersWithinBounds(
      @RequestParam("sw_lat") double southWestLat,
      @RequestParam("sw_lng") double southWestLon,
      @RequestParam("ne_lat") double northEastLat,
      @RequestParam("ne_lng") double northEastLon,
      @RequestParam("zoom") double zoom) {
    var totalQuantity = realtyService.countAllRealtyObjects();

    var realty = realtyService.findRealtyClustersWithinBounds(
        southWestLat, southWestLon, northEastLat, northEastLon, zoom);

    var cityIds = realty.stream()
        .map(RealtyCluster::getCityId)
        .collect(toSet());

    var allRealtyPriceStatistics = realtyService.findAllRealtyPriceStatistics();

    var filteredRealtyPriceStatistics = allRealtyPriceStatistics.entrySet()
        .stream()
        .filter(entry -> cityIds.contains(entry.getKey()))
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    return new RealtyClusterResponse(totalQuantity, filteredRealtyPriceStatistics, realty);
  }

  public static void main(String[] args) {
    for (int x = 3; x <= 18; x++) {
      double a = 10 * Math.pow(10, 1.0 / 5);
      double b = -Math.log(10) / 15;
      System.out.println(a * Math.exp(b * x));
    }
  }
}
