package com.planet.h2repository.business;

public class Edge {
    public  String v1, v2;
    public  int dist;
    
    public Edge() {
	}

	public Edge(String v1, String v2, int dist) {
       this.v1 = v1;
       this.v2 = v2;
       this.dist = dist;
    }

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "Edge [v1=" + v1 + ", v2=" + v2 + ", dist=" + dist + "]";
	}

	
    
 }
