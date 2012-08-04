package br.com.mr.pathfinder.server.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernatespatial.criterion.SpatialRestrictions;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.pathfinder.server.entities.BusStop;
import br.com.mr.pathfinder.server.util.SpatialUtils;
import br.com.mr.pathfinder.server.util.Util;

import com.vividsolutions.jts.geom.Geometry;

@Named
public class DaoBusStop extends Dao<BusStop> {
	public DaoBusStop() {
		clazz = BusStop.class;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List<BusStop> findNextBusStops(Double latitude, Double longitude) {
		Geometry point = SpatialUtils.createPoint(latitude, longitude);
		List list =  criteria().add(SpatialRestrictions.distanceWithin("geoLocation", point, Util.DISTANCE_1_KM)).list();
		return list;
	}
}
