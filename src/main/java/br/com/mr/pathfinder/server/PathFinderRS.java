package br.com.mr.pathfinder.server;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/finder")
public class PathFinderRS {

	@Inject
	BusStopService busStopService;

	public PathFinderRS() {

	}
	@GET
	@Path("/busstop/{input}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping(@PathParam("input") String input) {
		return Response.ok().entity(input).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/findpoints")
	public Response findBusStops(GeograficPoint point) {		
//		return Response.ok().entity(busStopService.findNextBusStops(point)).build();
		return Response.ok().entity(point).build();
	}
}
