package br.com.mr.pathfinder.server;

import java.util.List;

import br.com.mr.pathfinder.server.entities.BusStop;

public interface BusStopService {

	List<BusStopTO> findNextBusStops(GeograficPoint point);
}
