package geohashexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Coordinates {

  @AllArgsConstructor
  private static class Range {

    double start;
    double end;

    double getMiddle() {
      return (start + end) / 2;
    }

    int divideByValue(double value) {
      var middle = getMiddle();
      if (value >= middle) {
        this.start = middle;
        return 1;
      } else {
        this.end = middle;
        return 0;
      }
    }

    void divideByBit(int bit) {
      var middle = getMiddle();
      if (bit > 0) {
        this.start = middle;
      } else {
        this.end = middle;
      }
    }
  }

  private static final String BASE_32 = "0123456789bcdefghjkmnpqrstuvwxyz";

  private final double latitude;
  private final double longitude;

  public String toGeohash(int precision) {
    var latRange = new Range(-90.0, 90.0);
    var lonRange = new Range(-180.0, 180.0);
    var isEven = true;
    var bit = 0;
    var base32CharIndex = 0;
    var geohash = new StringBuilder();

    while (geohash.length() < precision) {
      if (isEven) {
        base32CharIndex = (base32CharIndex << 1) | lonRange.divideByValue(longitude);
      } else {
        base32CharIndex = (base32CharIndex << 1) | latRange.divideByValue(latitude);
      }

      isEven = !isEven;

      if (bit < 4) {
        bit++;
      } else {
        geohash.append(BASE_32.charAt(base32CharIndex));
        bit = 0;
        base32CharIndex = 0;
      }
    }

    return geohash.toString();
  }

  public static Coordinates fromGeohash(String geohash) {
    var latRange = new Range(-90.0, 90.0);
    var lonRange = new Range(-180.0, 180.0);
    var isEvenBit = true;

    for (var i = 0; i < geohash.length(); i++) {
      int base32CharIndex = BASE_32.indexOf(geohash.charAt(i));
      for (var j = 4; j >= 0; j--) {
        if (isEvenBit) {
          lonRange.divideByBit((base32CharIndex >> j) & 1);
        } else {
          latRange.divideByBit((base32CharIndex >> j) & 1);
        }
        isEvenBit = !isEvenBit;
      }
    }

    return new Coordinates(latRange.getMiddle(), lonRange.getMiddle());
  }
}