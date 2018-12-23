/*
 * Copyright 2018 Evgeniy Khyst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geohashexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
public class Realty {

  public static final int GEOHASH_PRECISION = 10;

  @Id
  @Column("realty_id")
  private Long id;

  private double latitude;

  private double longitude;

  private String geohash;

  private double price;

  private Integer cityId;

  public Realty(Coordinates coordinates, double price) {
    this.latitude = coordinates.getLatitude();
    this.longitude = coordinates.getLongitude();
    this.geohash = coordinates.toGeohash(GEOHASH_PRECISION);
    this.price = price;
  }
}
