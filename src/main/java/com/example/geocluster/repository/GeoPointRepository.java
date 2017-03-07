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
package com.example.geocluster.repository;

import com.example.geocluster.model.GeoCluster;
import com.example.geocluster.model.Coordinates;
import com.example.geocluster.model.GeoPoint;
import java.util.List;

/**
 *
 * @author evgeniy
 */
public interface GeoPointRepository {

    void save(GeoPoint geoPoint);
    
    List<GeoCluster> findGeoClusters(Coordinates southWest, Coordinates northEast, int geohashLength);
    
}
