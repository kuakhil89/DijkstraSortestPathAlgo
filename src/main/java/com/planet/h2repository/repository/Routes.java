package com.planet.h2repository.repository;

import java.io.Serializable;

public class Routes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int routeId;
	private String planetOrigin;
	private String planetDestination;
	private double distance;
	
	public Routes() {
		
	}
	
	public Routes(int routeId, String planetOrigin, String planetDestination, double distance) {
		super();
		this.routeId = routeId;
		this.planetOrigin = planetOrigin;
		this.planetDestination = planetDestination;
		this.distance = distance;
	}

	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getPlanetOrigin() {
		return planetOrigin;
	}
	public void setPlanetOrigin(String planetOrigin) {
		this.planetOrigin = planetOrigin;
	}
	public String getPlanetDestination() {
		return planetDestination;
	}
	public void setPlanetDestination(String planetDestination) {
		this.planetDestination = planetDestination;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Routes [routeId=");
		builder.append(routeId);
		builder.append(", planetOrigin=");
		builder.append(planetOrigin);
		builder.append(", planetDestination=");
		builder.append(planetDestination);
		builder.append(", distance=");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}
	
	

}
