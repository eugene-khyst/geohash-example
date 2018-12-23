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

package geohashexample.service;

import static java.util.stream.Collectors.toMap;

import geohashexample.model.City;
import geohashexample.model.Realty;
import geohashexample.model.RealtyCluster;
import geohashexample.model.RealtyPriceStatistics;
import geohashexample.repository.CityRepository;
import geohashexample.repository.RealtyRepository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class RealtyService {

  private static final String REALTY_PRICE_STDDEV_CACHE_NAME = "realty-price-stddev";

  private final RealtyDataProvider realtyDataProvider;
  private final CityRepository cityRepository;
  private final RealtyRepository realtyRepository;
  private final ZoomToGeohashPrecisionConverter zoomToGeohashPrecisionConverter;

  @CacheEvict(REALTY_PRICE_STDDEV_CACHE_NAME)
  @Transactional
  public void reimportRealty() {
    log.info("Starting realty reimport...");
    long start = System.currentTimeMillis();

    realtyRepository.deleteAll();
    log.info("Deleted all realty objects");

    var cities = cityRepository.findAll()
        .stream()
        .collect(toMap(City::getName, Function.identity()));

    var realtyCount = new LongAdder();
    realtyDataProvider.getRealty((City newCity, Realty newRealty) -> {
      City city = cities.computeIfAbsent(newCity.getName(), name -> cityRepository.save(newCity));
      newRealty.setCityId(city.getId());
      realtyRepository.save(newRealty);
      realtyCount.increment();
    });

    long executionTime = System.currentTimeMillis() - start;
    log.info("Successfully imported {} realty objects in {} ms", realtyCount.sum(), executionTime);
  }

  public List<RealtyCluster> findRealtyClustersWithinBounds(
      double southWestLat, double southWestLon, double northEastLat, double northEastLon,
      double zoom) {
    int precision = zoomToGeohashPrecisionConverter.toGeohashPrecision(zoom);
    return realtyRepository.findRealtyClustersWithinBounds(
        southWestLat, southWestLon, northEastLat, northEastLon, precision);
  }

  @Cacheable(REALTY_PRICE_STDDEV_CACHE_NAME)
  public Map<Integer, RealtyPriceStatistics> findAllRealtyPriceStatistics() {
    return realtyRepository.findAllRealtyPriceStatistics()
        .stream()
        .collect(toMap(RealtyPriceStatistics::getCityId, Function.identity()));
  }
}
