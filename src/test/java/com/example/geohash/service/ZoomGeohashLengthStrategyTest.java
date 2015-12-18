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

import static com.example.geohash.service.ZoomGeohashLengthStrategy.DEFAULT_MAX_GEOHASH_LENGTH;
import static com.example.geohash.service.ZoomGeohashLengthStrategy.DEFAULT_MAX_ZOOM;
import static com.example.geohash.service.ZoomGeohashLengthStrategy.DEFAULT_MIN_GEOHASH_LENGTH;
import static com.example.geohash.service.ZoomGeohashLengthStrategy.DEFAULT_MIN_ZOOM;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author evgeniy
 */
public class ZoomGeohashLengthStrategyTest {

    @Test
    public void testSomeMethod() {
        GeohashLengthStrategy geohashLengthStrategy = new ZoomGeohashLengthStrategy();
        assertEquals(DEFAULT_MIN_GEOHASH_LENGTH, geohashLengthStrategy.getGeohashLength(null, null, DEFAULT_MIN_ZOOM - 1));
        assertEquals(DEFAULT_MIN_GEOHASH_LENGTH, geohashLengthStrategy.getGeohashLength(null, null, DEFAULT_MIN_ZOOM));
        assertEquals(DEFAULT_MAX_GEOHASH_LENGTH, geohashLengthStrategy.getGeohashLength(null, null, DEFAULT_MAX_ZOOM));
        assertEquals(DEFAULT_MAX_GEOHASH_LENGTH, geohashLengthStrategy.getGeohashLength(null, null, DEFAULT_MAX_ZOOM + 1));
    }
}
