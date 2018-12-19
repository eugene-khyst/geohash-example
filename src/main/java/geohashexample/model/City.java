package geohashexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
public class City {

  @Id
  @Column("city_id")
  private Integer id;

  private String name;

  public City(String name) {
    this.name = name;
  }
}
