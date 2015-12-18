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

import com.example.geohash.model.Coordinates;
import javax.inject.Singleton;

/**
 *
 * @author evgeniy
 */
@Singleton
public class ZoomGeohashLengthStrategy implements GeohashLengthStrategy {

    public static final int DEFAULT_MIN_GEOHASH_LENGTH = 1;
    public static final int DEFAULT_MAX_GEOHASH_LENGTH = 12;
    public static final int DEFAULT_MIN_ZOOM = 0;
    public static final int DEFAULT_MAX_ZOOM = 18;

    private int minGeohashLength = DEFAULT_MIN_GEOHASH_LENGTH;
    private int maxGeohashLength = DEFAULT_MAX_GEOHASH_LENGTH;
    private int minZoom = DEFAULT_MIN_ZOOM;
    private int maxZoom = DEFAULT_MAX_ZOOM;

    @Override
    public int getGeohashLength(Coordinates southWest, Coordinates northEast, int zoom) {
        double a = minGeohashLength / Math.exp(minZoom / (maxZoom - minZoom) * Math.log(maxGeohashLength / minGeohashLength));
        double b = Math.log(maxGeohashLength / minGeohashLength) / (maxZoom - minZoom);
        return (int) Math.max(minGeohashLength, Math.min(a * Math.exp(b * zoom), maxGeohashLength));
    }

    public int getMinGeohashLength() {
        return minGeohashLength;
    }

    public void setMinGeohashLength(int minGeohashLength) {
        this.minGeohashLength = minGeohashLength;
    }

    public int getMaxGeohashLength() {
        return maxGeohashLength;
    }

    public void setMaxGeohashLength(int maxGeohashLength) {
        this.maxGeohashLength = maxGeohashLength;
    }

    public int getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(int minZoom) {
        this.minZoom = minZoom;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }
}
