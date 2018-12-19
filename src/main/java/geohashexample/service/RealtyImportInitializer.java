package geohashexample.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RealtyImportInitializer {

  private final RealtyImportInitializerProperties properties;
  private final RealtyService realtyService;

  @PostConstruct
  public void init() {
    if (properties.isEnabled()) {
      realtyService.reimportRealty();
    } else {
      log.info("Skipping realty reimport");
    }
  }
}
