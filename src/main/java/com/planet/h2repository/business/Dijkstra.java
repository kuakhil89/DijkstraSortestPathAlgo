package com.planet.h2repository.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.planet.h2repository.business.Graph.Edge;
import com.planet.h2repository.repository.PlanetJdbcRepository;
import com.planet.h2repository.repository.Routes;

@Service
public class Dijkstra {

	@Autowired
	 PlanetJdbcRepository planetJdbcRepository1;
	
	public StringBuffer getAllRoutes(String source,String destination) {
		
		System.out.println("Right now in business and calling repository");
		
		List<Routes> routes=planetJdbcRepository1.findAllRoutes();
		if(null!=routes && !routes.isEmpty()) {
		List<Edge> edge=new ArrayList<Edge>();
		for(Routes r : routes) {
			Edge e=new Edge();
			e.setV1(r.getPlanetOrigin());
			e.setV2(r.getPlanetDestination());
			e.setDist((int) r.getDistance());
			edge.add(e);
			System.out.println(edge);
		}
		System.out.println(edge);
		Graph g = new Graph(edge);
	      g.dijkstra(source);
	     StringBuffer s= g.printPath(destination);
		
		return s;
		}
		return null;
	}
		   
}
