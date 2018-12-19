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
  void test(int zoom, int precision) {
    var converter = new SimpleZoomToGeohashPrecisionConverter();
    assertThat(precision).isEqualTo(converter.toGeohashPrecision(zoom));
  }

  static Stream<Arguments> zoomAndPrecisionProvider() {
    return Stream.of(
        arguments(0, 1),
        arguments(1, 1),
        arguments(2, 1),
        arguments(3, 1),
        arguments(4, 1),
        arguments(5, 1),
        arguments(6, 2),
        arguments(7, 2),
        arguments(8, 3),
        arguments(9, 3),
        arguments(10, 4),
        arguments(11, 5),
        arguments(12, 5),
        arguments(13, 6),
        arguments(14, 6),
        arguments(15, 7),
        arguments(16, 8),
        arguments(17, 9),
        arguments(18, 10),
        arguments(19, 11),
        arguments(20, 12),
        arguments(21, 12)
    );
  }
}
