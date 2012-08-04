package br.com.mr.pathfinder.server.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public class SpatialUtils {

	static int SRID = 4326;

	private static PrecisionModel p = new PrecisionModel(PrecisionModel.FLOATING);
	private static GeometryFactory factory = new GeometryFactory(p, SRID);

	public static Geometry createPoint(Double latitude, Double longitude) {
		
		return factory.createPoint(new Coordinate( longitude, latitude));
	}
}
