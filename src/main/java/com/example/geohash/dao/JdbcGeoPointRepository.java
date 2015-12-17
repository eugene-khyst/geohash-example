/*
 * Copyright 2015 evgeniy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.geohash.dao;

import com.example.geohash.model.Coordinates;
import com.example.geohash.model.GeoCluster;
import com.example.geohash.model.GeoPoint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 *
 * @author evgeniy
 */
@Transactional(MANDATORY)
@Singleton
public class JdbcGeoPointRepository implements GeoPointRepository {

    private static final String INSERT_GEO_POINT_SQL
            = "INSERT INTO GEO_POINT(LATITUDE_DEG, LONGITUDE_DEG, GEOHASH, COUNTRY_CODE)"
            + " VALUES(?, ?, ?, ?)";

    private static final String SELECT_GEO_CLUSTERS_SQL
            = "  SELECT AVG(GP.LATITUDE_DEG) AS LATITUDE_DEG, \n"
            + "         AVG(GP.LONGITUDE_DEG) AS LONGITUDE_DEG, \n"
            + "         COUNT(*) AS QUANTITY, \n"
            + "         SUBSTRING(GP.GEOHASH FROM 1 FOR ?) AS GEOHASH_PREFIX \n"
            + "    FROM GEO_POINT GP \n"
            + "   WHERE GP.LATITUDE_DEG BETWEEN ? AND ? \n"
            + "     AND GP.LONGITUDE_DEG BETWEEN ? AND ? \n"
            + "GROUP BY GEOHASH_PREFIX";

    @Inject
    private DataSource dataSource;

    @Override
    public void save(GeoPoint geoPoint) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT_GEO_POINT_SQL);
            ps.setDouble(1, geoPoint.getCoordinates().getLatitude());
            ps.setDouble(2, geoPoint.getCoordinates().getLongitude());
            ps.setString(3, geoPoint.getGeohash());
            ps.setString(4, geoPoint.getCountryCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GeoCluster> findGeoClusters(Coordinates southWest, Coordinates northEast, int geohashLength) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SELECT_GEO_CLUSTERS_SQL);
            ps.setInt(1, geohashLength);
            ps.setDouble(2, southWest.getLatitude());
            ps.setDouble(3, northEast.getLatitude());
            ps.setDouble(4, southWest.getLongitude());
            ps.setDouble(5, northEast.getLongitude());
            ResultSet rs = ps.executeQuery();

            List<GeoCluster> results = new ArrayList<>();
            while (rs.next()) {
                double latitude = rs.getDouble("LATITUDE_DEG");
                double longitude = rs.getDouble("LONGITUDE_DEG");
                long quantity = rs.getLong("QUANTITY");
                String geohashPrefix = rs.getString("GEOHASH_PREFIX");
                String countryCode = rs.getString("COUNTRY_CODE");

                results.add(new GeoCluster(new Coordinates(latitude, longitude), quantity, geohashPrefix, countryCode));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
