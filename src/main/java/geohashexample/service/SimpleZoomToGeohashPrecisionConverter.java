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

package geohashexample.service;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class SimpleZoomToGeohashPrecisionConverter implements ZoomToGeohashPrecisionConverter {

  private static final int FIRST_ZOOM = 4;
  private static final int LAST_ZOOM = 22;

  @Override
  public int toGeohashPrecision(double zoom) {
    int intZoom = (int) zoom;
    if (intZoom < FIRST_ZOOM) {
      intZoom = FIRST_ZOOM;
    } else if (intZoom > LAST_ZOOM) {
      intZoom = LAST_ZOOM;
    }
    return intZoom / 2 - 1;
  }
}
