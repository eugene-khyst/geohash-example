/*
 * Copyright 2018 Evgeniy Khyst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    return new RealtyClusterResponse(filteredRealtyPriceStatistics, realty);
  }
}
