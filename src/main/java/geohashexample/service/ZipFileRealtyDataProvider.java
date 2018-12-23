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

import geohashexample.model.City;
import geohashexample.model.Coordinates;
import geohashexample.model.Realty;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.zip.ZipFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ZipFileRealtyDataProvider implements RealtyDataProvider {

  private final ZipFileRealtyDataProviderProperties properties;

  @Override
  public void getRealty(BiConsumer<City, Realty> consumer) {
    log.info("Reading file {} from {} archive", properties.getEntryName(), properties.getLocation());

    try (var zipFile = new ZipFile(properties.getLocation())) {
      var zipEntry = zipFile.getEntry(properties.getEntryName());
      var inputStream = zipFile.getInputStream(zipEntry);
      try (var scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
        scanner.useDelimiter("\\n");
        while (scanner.hasNext()) {
          var line = scanner.next();
          var cells = line.split(";");

          var cityName = cells[0];
          var city = new City(cityName);

          var latitude = Double.parseDouble(cells[1]);
          var longitude = Double.parseDouble(cells[2]);
          var price = Double.parseDouble(cells[3].substring(1));
          var realty = new Realty(new Coordinates(latitude, longitude), price);

          consumer.accept(city, realty);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
