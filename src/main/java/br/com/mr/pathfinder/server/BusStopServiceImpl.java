package br.com.mr.pathfinder.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.pathfinder.server.dao.DaoBusStop;
import br.com.mr.pathfinder.server.entities.BusStop;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

@Named
@Transactional
public class BusStopServiceImpl implements BusStopService {

	private static final Log LOGER = LogFactory.getLog(BusStopServiceImpl.class);
	@Inject
	DaoBusStop daoBusStop;

	@Override
	public List<BusStopTO> findNextBusStops(GeograficPoint point) {
		LOGER.info("Buscando pontos...");
		List<BusStop> busStops = null;
		List<BusStopTO> returnList = new ArrayList<BusStopTO>();
		try {
			busStops = daoBusStop.findNextBusStops(point.getLatitude(), point.getLongitude());

			for (BusStop busStopTO : busStops) {
				returnList.add(createTOFromBusStop(busStopTO));
			}
		} catch (Exception e) {
			LOGER.error("Erro ao obter Pontos", e);
		}
		busStops.clear();
		return returnList;
	}

	private BusStopTO createTOFromBusStop(BusStop busStop) {
		BusStopTO busStopTO = new BusStopTO();

		busStopTO.setId(busStop.getId());
		busStopTO.setAddressLocation(busStop.getAddressLocation());
		busStopTO.setDescription(busStop.getDescription());
		busStopTO.setGeoLocation(createTOfromPoint(busStop.getGeoLocation()));

		return busStopTO;
	}

	private PointTO createTOfromPoint(Geometry geoLocation) {
		Coordinate[] coordinates = geoLocation.getCoordinates();
		PointTO pointTO = null;
		if (coordinates != null && coordinates.length >= 1) {
			pointTO = new PointTO(coordinates[0].y, coordinates[0].x);
		}
		return pointTO;
	}
}
