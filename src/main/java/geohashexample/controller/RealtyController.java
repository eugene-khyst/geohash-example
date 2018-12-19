package geohashexample.controller;

import geohashexample.model.RealtyCluster;
import geohashexample.service.RealtyService;
import java.util.List;
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
  public List<RealtyCluster> findRealtyClustersWithinBounds(
      @RequestParam("sw_lat") double southWestLat,
      @RequestParam("sw_lon") double southWestLon,
      @RequestParam("ne_lat") double northEastLat,
      @RequestParam("ne_lon") double northEastLon,
      @RequestParam("zoom") int zoom) {
    List<RealtyCluster> realty = realtyService.findRealtyClustersWithinBounds(
        southWestLat, southWestLon, northEastLat, northEastLon, zoom);
    return realty;
  }
}
