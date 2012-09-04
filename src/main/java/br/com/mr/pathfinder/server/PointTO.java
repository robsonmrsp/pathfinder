package br.com.mr.pathfinder.server;

import java.io.Serializable;

public class PointTO implements Serializable {
	
	private static final long serialVersionUID = 275003368464194283L;
	private Double latitude;
	private Double longitude;

	public PointTO() {}

	public PointTO(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
