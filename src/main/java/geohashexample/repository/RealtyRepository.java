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

package geohashexample.repository;

import geohashexample.model.Realty;
import geohashexample.model.RealtyCluster;
import geohashexample.model.RealtyPriceStatistics;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RealtyRepository extends CrudRepository<Realty, Long> {

  @Query("SELECT"
      + " CITY_ID,"
      + " AVG(LATITUDE) latitude,"
      + " AVG(LONGITUDE) longitude,"
      + " SUBSTRING(GEOHASH FROM 1 FOR :precision) geohash_prefix,"
      + " COUNT(*) quantity,"
      + " AVG(PRICE) average_price"
      + " FROM REALTY"
      + " WHERE LATITUDE BETWEEN :southWestLat AND :northEastLat"
      + " AND LONGITUDE BETWEEN :southWestLon AND :northEastLon"
      + " GROUP BY CITY_ID, geohash_prefix")
  List<RealtyCluster> findRealtyClustersWithinBounds(
      @Param("southWestLat") double southWestLat,
      @Param("southWestLon") double southWestLon,
      @Param("northEastLat") double northEastLat,
      @Param("northEastLon") double northEastLon,
      @Param("precision") int precision);

  @Query("SELECT"
      + " CITY_ID,"
      + " MIN(PRICE) minimum,"
      + " MAX(PRICE) maximum,"
      + " AVG(PRICE) mean,"
      + " STDDEV_POP(PRICE) standard_deviation"
      + " FROM REALTY"
      + " GROUP BY CITY_ID")
  List<RealtyPriceStatistics> findAllRealtyPriceStatistics();
}
