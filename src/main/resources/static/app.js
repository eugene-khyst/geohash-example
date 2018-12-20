var map = L.map('map', {
      zoomDelta: 0.5,
      zoomSnap: 0.5,
      wheelPxPerZoomLevel: 100
    }),
    realtyLayer = L.layerGroup();

L.tileLayer('https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}' + (L.Browser.retina ? '@2x.png' : '.png'), {
  attribution:'&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>, &copy; <a href="https://carto.com/attributions">CARTO</a>',
  subdomains: 'abcd',
  maxZoom: 18,
  minZoom: 3
}).addTo(map);

function gradient(position, colorStops) {
  var i, cs1, cs2, c1, c2, r, g, b;
  position = Math.min(Math.max(position, colorStops[0].pos), colorStops[colorStops.length - 1].pos);
  for (i = 0; i < colorStops.length - 1; i++) {
    if (colorStops[i + 1].pos >= position) {
      cs1 = colorStops[i];
      cs2 = colorStops[i + 1];
      c2 = (position - cs1.pos) / (cs2.pos - cs1.pos);
      c1 = 1 - c2;
      r = Math.floor(cs1.r * c1 + cs2.r * c2);
      g = Math.floor(cs1.g * c1 + cs2.g * c2);
      b = Math.floor(cs1.b * c1 + cs2.b * c2);
      return 'rgb(' + [r, g, b].join(',') + ')';
    }
  }
  throw 'Color stops for position ' + position + ' are not found';
}

function priceCoefficient(stat, cluster) {
  var min = Math.max(stat.min, stat.avg - 3 * stat.sd),
      max = Math.min(stat.max, stat.avg + 3 * stat.sd),
      avg = stat.avg,
      p = cluster.avgPrice;
  if (p == avg) {
    return 0.5;
  } else if (p < avg) {
    return 0.5 - (p - min) / (avg - min);
  } else if (p > avg) {
    return 0.5 + (max - p) / (max - avg);
  }
}

function clusterColor(stat, cluster) {
  return gradient(priceCoefficient(stat, cluster), [
    {pos: 0, r: 0, g: 255, b: 0},
    {pos: 0.5, r: 255, g: 255, b: 0},
    {pos: 1, r: 255, g: 0, b: 0}
  ]);
}

function drawRealty(e) {
  var bounds = map.getBounds(),
      zoom = map.getZoom();

  axios.get('/api/realty', {
    params: {
      sw_lat: bounds.getSouthWest().lat,
      sw_lng: bounds.getSouthWest().lng,
      ne_lat: bounds.getNorthEast().lat,
      ne_lng: bounds.getNorthEast().lng,
      zoom: zoom
    }
  })
  .then(function (response) {
    //stat.qnt
    var newRealtyLayer = L.layerGroup();
    response.data.realty.forEach(function (cluster) {
      //cluster.qnt;
      var stat = response.data.stats[cluster.cityId],
          color = clusterColor(stat, cluster),
          circle = L.circle([cluster.lat, cluster.lng], {
            color: color,
            fillColor: color,
            fillOpacity: 0.5,
            radius: 100
          })
          .addTo(newRealtyLayer);
    });

    realtyLayer.clearLayers();
    map.removeLayer(realtyLayer);
    realtyLayer = newRealtyLayer.addTo(map);
  })
  .catch(function (error) {
    console.log(error);
  });
}

map.on('load moveend', drawRealty);

map.setView([50.4501, 30.5234], 11);
