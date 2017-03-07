package db.migration;

import com.example.geocluster.model.GeoPoint;
import com.example.geocluster.geohash.GeohashUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * Created by evgeniy on 07.03.17.
 */
public class InitialMigrationGenerator {

    public static final String GEO_POINTS_CSV = "/geo-points.csv";
    public static final String DELIMITER = ",";
    public static final String SQL_TEMPLATE = "INSERT INTO GEO_POINT (LATITUDE_DEG, LONGITUDE_DEG, GEOHASH, COUNTRY_CODE) " +
            "VALUES ({0}, {1}, ''{2}'', ''{3}'');";

    public static void main(String[] args) throws Exception {
        Path path = Paths.get(InitialMigrationGenerator.class.getResource(GEO_POINTS_CSV).toURI());
        Files.readAllLines(path).stream()
                .map(line -> line.split(DELIMITER))
                .map(arr -> MessageFormat.format(SQL_TEMPLATE,
                        arr[0],
                        arr[1],
                        GeohashUtils.encodeGeohash(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]), GeoPoint.GEOHASH_LENGTH),
                        arr[2]))
                .forEach(System.out::println);
    }
}
