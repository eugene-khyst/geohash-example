Server-side Clustering of Geo Points Using Geohash Example
==========================================================

When there are a lot of geo objects on map they are barely distinguishable and merged into one big spot. 
A lot of memory is used to store geo object coordinates and other data. 
Displaying map with many points consumes a lot of hardware resources so applications frequently hang.

Standard solution to this problem is to union objects located nearby into group called cluster with special icon where the number of elements in cluster is specified.
When zoomed cluster is splitted into separate points or other clusters.

This example shows how to implement server-side clustering of geo points on a map using Geohash.

To get more details read about [Geohash encoding and decoding algorithm](http://developer-should-know.tumblr.com/post/87283491372/geohash-encoding-and-decoding-algorithm) and [server-side clustering of geo points using Geohash](http://developer-should-know.tumblr.com/post/90338187947/server-side-clustering-of-geo-points-using-geohash).

Build
-----

```
mvn clean package -Pdocker
```

Run
---

```
docker-compose up -d
```

Use
---

```
http://localhost:8000
```

```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/geo-cluster?south_west_lat=33.797408767572485&south_west_lon=-19.51171875&north_east_lat=62.14497603754045&north_east_lon=59.58984374999999&zoom=4'
```

```
curl -X POST --header 'Content-Type: application/x-www-form-urlencoded' --header 'Accept: application/json' -d 'lat=50.4546600&lon=30.5238000&country_code=UA' 'http://localhost:8080/api/geo-point'
```

Swagger
-------

```
http://localhost:8080/swagger.json
```

```
http://localhost:8080/swagger-ui
```

