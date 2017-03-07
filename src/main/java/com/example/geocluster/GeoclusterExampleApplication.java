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
package com.example.geocluster;

import com.example.geocluster.geohash.strategy.GeohashLengthStrategy;
import com.example.geocluster.model.Coordinates;
import com.example.geocluster.model.GeoCluster;
import com.example.geocluster.model.GeoPoint;
import com.example.geocluster.repository.GeoPointRepository;
import io.swagger.annotations.Api;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author evgeniy
 */
@ApplicationScoped
@Path("/api")
@Api("geocluster-example")
public class GeoclusterExampleApplication {

    @Inject
    private GeoPointRepository geoPointRepository;

    @Inject
    private GeohashLengthStrategy geohashLengthStrategy;

    @POST
    @Path("/geo-point")
    @Produces(APPLICATION_JSON)
    public Response createGeoPoint(
            @FormParam("lat") double latitude,
            @FormParam("lon") double longitude,
            @FormParam("country_code") String countryCode) {
        GeoPoint geoPoint = GeoPoint.fromCoordinates(new Coordinates(latitude, longitude), countryCode);
        geoPointRepository.save(geoPoint);
        return Response.ok(geoPoint).build();
    }

    @GET
    @Path("/geo-cluster")
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
