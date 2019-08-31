package com.planet.h2repository.repository;

import java.io.Serializable;

public class PlanetNames implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 169472739823482787L;
	private String planetNode;
	private String planetName;
	
	
	public PlanetNames() {
	}
	public PlanetNames(String planetNode, String planetName) {
		super();
		this.planetNode = planetNode;
		this.planetName = planetName;
	}
	public String getPlanetNode() {
		return planetNode;
	}
	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlanetNames [planetNode=");
		builder.append(planetNode);
		builder.append(", planetName=");
		builder.append(planetName);
		builder.append("]");
		return builder.toString();
	}
	
	

}
