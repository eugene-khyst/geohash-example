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

import static com.example.geocluster.geohash.GeohashUtils.encodeGeohash;

import com.example.geocluster.geohash.GeohashUtils;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author evgeniy
 */
public class GeohashUtilsTest {
    
    @Test
    public void testEncodeGeohash() {
        assertEquals("u4pruydqqvj8", GeohashUtils.encodeGeohash(57.64911, 10.40744, 12));
    }

    @Test
    public void testDecodeGeohash() {
        Coordinates coordinates = GeohashUtils.decodeGeohash("u4pruydqqvj8");
        assertEquals(57.64911, coordinates.getLatitude(), 0.000001);
        assertEquals(10.40744, coordinates.getLongitude(), 0.000001);
    }
}
