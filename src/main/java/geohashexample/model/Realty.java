package geohashexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
public class Realty {

  public static final int GEOHASH_PRECISION = 10;

  @Id
  @Column("realty_id")
  private Long id;

  private double latitude;

  private double longitude;

  private String geohash;

  private double price;

  private Integer cityId;

  public Realty(Coordinates coordinates, double price) {
    this.latitude = coordinates.getLatitude();
    this.longitude = coordinates.getLongitude();
    this.geohash = coordinates.toGeohash(GEOHASH_PRECISION);
    this.price = price;
  }
}
