package geohashexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GeohashExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(GeohashExampleApplication.class, args);
  }
}
