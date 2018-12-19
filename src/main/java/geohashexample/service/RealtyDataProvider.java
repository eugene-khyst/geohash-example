package geohashexample.service;

import geohashexample.model.City;
import geohashexample.model.Realty;
import java.util.function.BiConsumer;

public interface RealtyDataProvider {

  void getRealty(BiConsumer<City, Realty> consumer);
}
