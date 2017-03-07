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
package com.example.geocluster.model;

import com.example.geocluster.geohash.GeohashUtils;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author evgeniy
 */
@Data
public class GeoPoint implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final int GEOHASH_LENGTH = 12;
    
    private final Coordinates coordinates;
    private final String geohash;
    private final String countryCode;

    public static GeoPoint fromCoordinates(Coordinates coordinates, String countryCode) {
        return new GeoPoint(coordinates, GeohashUtils.encodeGeohash(coordinates, GEOHASH_LENGTH), countryCode);
    }
    
    public static GeoPoint fromCoordinates(Coordinates coordinates) {
        return fromCoordinates(coordinates, null);
    }
    
    public static GeoPoint fromGeohash(String geohash, String countryCode) {
        return new GeoPoint(GeohashUtils.decodeGeohash(geohash), geohash, countryCode);
    }
    
    public static GeoPoint fromGeohash(String geohash) {
        return fromGeohash(geohash, null);
    }
}
