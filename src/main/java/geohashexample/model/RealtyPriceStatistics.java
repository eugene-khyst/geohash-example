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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RealtyPriceStatistics {

  @JsonIgnore
  private final int cityId;

  @JsonProperty("min")
  private final double minimum;

  @JsonProperty("max")
  private final double maximum;

  @JsonProperty("avg")
  private final double mean;

  @JsonProperty("sd")
  private final double standardDeviation;
}
