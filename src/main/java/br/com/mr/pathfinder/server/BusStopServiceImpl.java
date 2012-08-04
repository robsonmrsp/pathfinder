package br.com.mr.pathfinder.server;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.mr.pathfinder.server.dao.DaoBusStop;
import br.com.mr.pathfinder.server.entities.BusStop;

@Named
@Transactional
public class BusStopServiceImpl implements BusStopService{

	@Inject
	DaoBusStop daoBusStop;
	
	@Override
	public List<BusStop> findNextBusStops(GeograficPoint point) {

		return daoBusStop.findNextBusStops(point.getLatitude(), point.getLongitude());
	}
}
