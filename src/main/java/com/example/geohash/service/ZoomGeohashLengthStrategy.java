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

    public static final int MIN_ZOOM_LEVEL = 0;
    public static final int MAX_ZOOM_LEVEL = 18;
    public static final int MIN_GEOHASH_LENGTH = 1;
    public static final int MAX_GEOHASH_LENGTH = 10;
    
    @Override
    public int getGeohashLength(Coordinates southWest, Coordinates northEast, int zoom) {
        return MIN_GEOHASH_LENGTH + ((MAX_ZOOM_LEVEL - MIN_ZOOM_LEVEL) * zoom) / (MAX_GEOHASH_LENGTH - MIN_GEOHASH_LENGTH);
    }
}
