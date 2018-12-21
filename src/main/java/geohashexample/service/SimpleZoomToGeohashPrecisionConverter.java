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
