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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SimpleZoomToGeohashPrecisionConverterTests {

  @ParameterizedTest
  @MethodSource("zoomAndPrecisionProvider")
  void test(double zoom, int precision) {
    var converter = new SimpleZoomToGeohashPrecisionConverter();
    assertThat(precision).isEqualTo(converter.toGeohashPrecision(zoom));
  }

  static Stream<Arguments> zoomAndPrecisionProvider() {
    return Stream.of(
        arguments(0, 1),
        arguments(0.5, 1),
        arguments(1, 1),
        arguments(1.5, 1),
        arguments(2, 1),
        arguments(2.5, 1),
        arguments(3, 1),
        arguments(3.5, 1),
        arguments(4, 1),
        arguments(4.5, 1),
        arguments(5, 1),
        arguments(5.5, 1),
        arguments(6, 2),
        arguments(6.5, 2),
        arguments(7, 2),
        arguments(7.5, 2),
        arguments(8, 3),
        arguments(8.5, 3),
        arguments(9, 3),
        arguments(9.5, 3),
        arguments(10, 4),
        arguments(10.5, 4),
        arguments(11, 4),
        arguments(11.5, 4),
        arguments(12, 5),
        arguments(12.5, 5),
        arguments(13, 5),
        arguments(13.5, 5),
        arguments(14, 6),
        arguments(14.5, 6),
        arguments(15, 6),
        arguments(15.5, 6),
        arguments(16, 7),
        arguments(16.5, 7),
        arguments(17, 7),
        arguments(17.5, 7),
        arguments(18, 8),
        arguments(18.5, 8),
        arguments(19, 8),
        arguments(19.5, 8),
        arguments(20, 9),
        arguments(20.5, 9),
        arguments(21, 9),
        arguments(21.5, 9),
        arguments(22, 10),
        arguments(22.5, 10)
    );
  }
}
