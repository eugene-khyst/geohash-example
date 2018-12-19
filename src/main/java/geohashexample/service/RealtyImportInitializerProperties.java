package geohashexample.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("realty-data-import.initializer")
public class RealtyImportInitializerProperties {

  private boolean enabled = true;
}
