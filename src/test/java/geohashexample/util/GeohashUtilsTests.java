package geohashexample.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GeohashUtilsTests {

  @ParameterizedTest
  @MethodSource("coordinatesAndGeohashProvider")
  void testEncode(Coordinates coordinates, int precision, String geohash) {
    assertThat(geohash).isEqualTo(GeohashUtils.encodeGeohash(coordinates, precision));
  }

  @ParameterizedTest
  @MethodSource("coordinatesAndGeohashProvider")
  void testEncodeAndDecode(Coordinates coordinates, int precision, String geohash) {
    Coordinates decoded = GeohashUtils.decodeGeohash(geohash);
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
