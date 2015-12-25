Server-side Clustering of Geo Points Using Geohash Example
==========================================================

When there are a lot of geo objects on map they are barely distinguishable and merged into one big spot. 
A lot of memory is used to store geo object coordinates and other data. 
Displaying map with many points consumes a lot of hardware resources so applications frequently hang.

Standard solution to this problem is to union objects located nearby into group called cluster with special icon where the number of elements in cluster is specified.
When zoomed cluster is splitted into separate points or other clusters.

This example shows how to implement server-side clustering of geo points on a map using Geohash.

To get more details read about [Geohash encoding and decoding algorithm](http://developer-should-know.tumblr.com/post/87283491372/geohash-encoding-and-decoding-algorithm) and [server-side clustering of geo points using Geohash](http://developer-should-know.tumblr.com/post/90338187947/server-side-clustering-of-geo-points-using-geohash).

Interactive example is available at http://geohash-evgeniykhist.rhcloud.com/.

