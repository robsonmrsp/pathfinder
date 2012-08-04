package br.com.mr.pathfinder.server.entities;

import java.io.Serializable;
import java.util.List;

public class Leg implements Serializable{

	Integer id;
	String name;
	String description;
	BusStop initIntinerary;
	BusStop endIntinerary;
	List<BusStop> busStops;
}
