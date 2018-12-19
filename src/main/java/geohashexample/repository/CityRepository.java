package geohashexample.repository;

import geohashexample.model.City;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {

  List<City> findAll();
}
