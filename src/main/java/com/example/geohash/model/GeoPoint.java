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
package com.example.geohash.model;

import static com.example.geohash.model.GeohashUtils.decodeGeohash;
import static com.example.geohash.model.GeohashUtils.encodeGeohash;
import java.io.Serializable;

/**
 *
 * @author evgeniy
 */
public class GeoPoint implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final int GEOHASH_LENGTH = 12;
    
    private final Coordinates coordinates;
    private final String geohash;
    private final String countryCode;

    private GeoPoint(Coordinates coordinates, String geohash, String countryCode) {
        this.coordinates = coordinates;
        this.geohash = geohash;
        this.countryCode = countryCode;
    }
    
    public static GeoPoint fromCoordinates(Coordinates coordinates, String countryCode) {
        return new GeoPoint(coordinates, encodeGeohash(coordinates, GEOHASH_LENGTH), countryCode);
    }
    
    public static GeoPoint fromCoordinates(Coordinates coordinates) {
        return fromCoordinates(coordinates, null);
    }
    
    public static GeoPoint fromGeohash(String geohash, String countryCode) {
        return new GeoPoint(decodeGeohash(geohash), geohash, countryCode);
    }
    
    public static GeoPoint fromGeohash(String geohash) {
        return fromGeohash(geohash, null);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getGeohash() {
        return geohash;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
