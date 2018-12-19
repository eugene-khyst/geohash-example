package geohashexample.service;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class SimpleZoomToGeohashPrecisionConverter implements ZoomToGeohashPrecisionConverter {

  private static final Map<Integer, Integer> ZOOM_TO_GEOHASH_PRECISION_MAP =
      Map.ofEntries(
          Map.entry(5, 1),
          Map.entry(6, 2),
          Map.entry(7, 2),
          Map.entry(8, 3),
          Map.entry(9, 3),
          Map.entry(10, 4),
          Map.entry(11, 5),
          Map.entry(12, 5),
          Map.entry(13, 6),
          Map.entry(14, 6),
          Map.entry(15, 7),
          Map.entry(16, 8),
          Map.entry(17, 9),
          Map.entry(18, 10),
          Map.entry(19, 11),
          Map.entry(20, 12)
      );

  private static final int FIRST_ZOOM = 5;
  private static final int LAST_ZOOM = 20;

  @Override
  public int toGeohashPrecision(int zoom) {
    if (zoom < FIRST_ZOOM) {
      zoom = FIRST_ZOOM;
    } else if (zoom > LAST_ZOOM) {
      zoom = LAST_ZOOM;
    }
    return ZOOM_TO_GEOHASH_PRECISION_MAP.get(zoom);
  }
}
