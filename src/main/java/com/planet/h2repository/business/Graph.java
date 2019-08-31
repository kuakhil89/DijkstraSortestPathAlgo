package com.planet.h2repository.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Graph {
	private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
	 
	/** Builds a graph from a set of edges */
	   public Graph(List<Edge> edge) {
	      graph = new HashMap<>(edge.size());
	      System.out.println(graph.size());
	 
	      //one pass to find all vertices
	      for (Edge e : edge) {
	         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
	         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
	      }
	 
	      //another pass to set neighbouring vertices
	      for (Edge e : edge) {
	         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
	         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
	      }
	   }
	
	   /* One edge of the graph (only used by Graph constructor) */
	   
	   
	   
	   /** Runs dijkstra using a specified source vertex */ 
	   public void dijkstra(String startName) {
	      if (!graph.containsKey(startName)) {
	         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
	         return;
	      }
	      final Vertex source = graph.get(startName);
	      NavigableSet<Vertex> q = new TreeSet<>();
	 
	      // set-up vertices
	      for (Vertex v : graph.values()) {
	         v.previous = v == source ? source : null;
	         v.dist = v == source ? 0 : Integer.MAX_VALUE;
	         q.add(v);
	      }
	 
	      dijkstra(q);
	   }
	   
	   /* Implementation of dijkstra's algorithm using a binary heap. */
	   private void dijkstra(final NavigableSet<Vertex> q) {      
	      Vertex u, v;
	      while (!q.isEmpty()) {
	 
	         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
	         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
	 
	         //look at distances to each neighbour
	         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
	            v = a.getKey(); //the neighbour in this iteration
	 
	            final int alternateDist = u.dist + a.getValue();
	            if (alternateDist < v.dist) { // shorter path to neighbour found
	               q.remove(v);
	               v.dist = alternateDist;
	               v.previous = u;
	               q.add(v);
	            } 
	         }
	      }
	   }
	   
	   /** Prints a path from the source to the specified vertex */
	   /*public void printPath(String endName) {
	      if (!graph.containsKey(endName)) {
	         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
	         return;
	      }
	 
	      graph.get(endName).printPath();
	      System.out.println();
	   }*/
	   // for returning path at browser
	   public StringBuffer printPath(String endName) {
		      if (!graph.containsKey(endName)) {
		         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
		         return null;
		      }
		      StringBuffer s1=new StringBuffer("");
		      StringBuffer s=graph.get(endName).printPath(s1);
		      System.out.println();
		      return s1;
		   }
	   
	   /** Prints the path from the source to every vertex (output order is not guaranteed) */
	   public void printAllPaths() {
		   StringBuffer s2=new StringBuffer("");
	      for (Vertex v : graph.values()) {
	         v.printPath(s2);
	         System.out.println();
	      }
	   }
	   

public static class Vertex implements Comparable<Vertex>{
	public final String name;
	public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	public Vertex previous = null;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();
 
	public Vertex(String name)
	{
		this.name = name;
	}
 
	/*private void printPath()
	{
		if (this == this.previous)
		{
			System.out.printf("%s", this.name);
		}
		else if (this.previous == null)
		{
			System.out.printf("%s(unreached)", this.name);
		}
		else
		{
			this.previous.printPath();
			System.out.printf(" -> %s(%d)", this.name, this.dist);
		}
	}*/
	
	//StringBuffer sb=new StringBuffer("Path Starts From=>");
	//StringBuffer sb2=new StringBuffer("->");
	private StringBuffer printPath(StringBuffer sb)
	{
		
		
		if (this == this.previous)
		{
			System.out.printf("%s", this.name);
			 sb.append(this.name);
			 return sb;
		}
		else if (this.previous == null)
		{
			System.out.printf("%s(unreached)", this.name);
			sb = sb.append(this.name);
			System.out.println(sb);
		}
		else
		{
			this.previous.printPath(sb);
			System.out.printf(" -> %s(%d)", this.name, this.dist);
			//System.out.println(sb);
			sb.append(" => "+this.name).append(" ("+String.valueOf(this.dist)+") ");
			//System.out.println(sb+"final path");
		}
		return sb;
	}
	
 
	public int compareTo(Vertex other)
	{
		if (dist == other.dist)
			return name.compareTo(other.name);
 
		return Integer.compare(dist, other.dist);
	}
 
	@Override public String toString()
	{
		return "(" + name + ", " + dist + ")";
	}
	
}

}
