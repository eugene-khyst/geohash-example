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
      + " COUNT(*) quantity,"
      + " MIN(PRICE) minimum,"
      + " MAX(PRICE) maximum,"
      + " AVG(PRICE) mean,"
      + " STDDEV_POP(PRICE) standard_deviation"
      + " FROM REALTY"
      + " GROUP BY CITY_ID")
  List<RealtyPriceStatistics> findAllRealtyPriceStatistics();
}
