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
package com.example.geohash.service;

import com.example.geohash.dao.GeoPointRepository;
import com.example.geohash.model.Coordinates;
import com.example.geohash.model.GeoCluster;
import com.example.geohash.model.GeoPoint;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author evgeniy
 */
@Path("/geo-point")
@Transactional
@Singleton
public class GeoPointResource {

    @Inject
    private GeoPointRepository geoPointRepository;

    @Inject
    private GeohashLengthStrategy geohashLengthStrategy;
    
    @POST
    @Path("/")
    public Response createGeoPoint(
            @FormParam("lat") double latitude,
            @FormParam("lon") double longitude,
            @FormParam("country_code") String countryCode) {
        GeoPoint geoPoint = GeoPoint.fromCoordinates(new Coordinates(latitude, longitude), countryCode);
        geoPointRepository.save(geoPoint);
        return Response.ok().build();
    }

    @GET
    @Path("/cluster/")
    @Produces(APPLICATION_JSON)
    public Response getGeoClusters(
            @QueryParam("south_west_lat") double southWestLat,
            @QueryParam("south_west_lon") double southWestLon,
            @QueryParam("north_east_lat") double northEastLat,
            @QueryParam("north_east_lon") double northEastLon,
            @QueryParam("zoom") int zoom) {
        Coordinates southWest = new Coordinates(southWestLat, southWestLon);
        Coordinates northEast = new Coordinates(northEastLat, northEastLon);
        int geohashLength = geohashLengthStrategy.getGeohashLength(southWest, northEast, zoom);
        List<GeoCluster> geoClusters = geoPointRepository.findGeoClusters(southWest, northEast, geohashLength);
        return Response.ok(geoClusters).build();
    }
}
