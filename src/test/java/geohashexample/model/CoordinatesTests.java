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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CoordinatesTests {

  @ParameterizedTest
  @MethodSource("coordinatesAndGeohashProvider")
  void testEncode(Coordinates coordinates, int precision, String geohash) {
    assertThat(geohash).isEqualTo(coordinates.toGeohash(precision));
  }

  @ParameterizedTest
  @MethodSource("coordinatesAndGeohashProvider")
  void testDecode(Coordinates coordinates, int precision, String geohash) {
    Coordinates decoded = Coordinates.fromGeohash(geohash);
    assertThat(coordinates.getLatitude()).isCloseTo(decoded.getLatitude(), within(0.001));
    assertThat(coordinates.getLongitude()).isCloseTo(decoded.getLongitude(), within(0.001));
  }

  static Stream<Arguments> coordinatesAndGeohashProvider() {
    return Stream.of(
        arguments(new Coordinates(48.669, -4.329), 5, "gbsuv"),
        arguments(new Coordinates(50.3519, 30.9294), 7, "u8vyry6")
    );
  }
}
