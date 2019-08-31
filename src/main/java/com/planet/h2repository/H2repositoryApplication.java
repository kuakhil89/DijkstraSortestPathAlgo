package com.planet.h2repository;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planet.h2repository.business.Dijkstra;
import com.planet.h2repository.repository.PlanetNames;
import com.planet.h2repository.service.ExcelReader;

@SpringBootApplication
@RestController
public class H2repositoryApplication {
	
	@Autowired
	ExcelReader excelReader;
	
	@Autowired
	Dijkstra dijkstra;

	public static void main(String[] args) {
		SpringApplication.run(H2repositoryApplication.class, args);
	}
	@RequestMapping(value = "/insertData")
	   public String enterDataInH2() throws InvalidFormatException, IOException {
	      
		System.out.println("Reading Excel file");
		excelReader.read();
		return "Planet and Route Data is Inserted Successfully";
	   }
	@RequestMapping(value = "/allPlanet")
	   public List<PlanetNames> getDataFromH2() throws InvalidFormatException, IOException {
	      
		System.out.println("Retriving Data from H2");
		return excelReader.getPlanetData();
		//return "All the planet data is retrived from Planet Table Successfully";
	   }
	
	//http://localhost:8080/getPlanetById/A
	@RequestMapping(value = "/getPlanetById/{planetNode}")
	   public PlanetNames getPlanetById(@PathVariable("planetNode") String planetNode) throws InvalidFormatException, IOException {
	      
		System.out.println("Retriving Planet by PlanetNode using PathVariable");
		return excelReader.getPlanetByNode(planetNode);
		
	   }
	
	//http://localhost:8080/getPlanetByPlanetNode?planetNode=B
	@RequestMapping(value = "/getPlanetByPlanetNode")
	   public PlanetNames getPlanetByPlanetNode(@RequestParam("planetNode") String planetNode) {
	      
		System.out.println("Retriving Planet by PlanetNode Using RequestParam");
		PlanetNames p=  excelReader.getPlanetByNode(planetNode);
		if(p!=null)
		{
			return p;
		}
		 return null;
		
	   }
	//http://localhost:8080/deleteByPlanetNode?planetNode=B
	@RequestMapping(value = "/deleteByPlanetNode")
	   public String deletePlanet(@RequestParam("planetNode") String planetNode) {
	      
		System.out.println("Planet is deleted");
		int i= excelReader.deleteByPlanetNode(planetNode);
		if(i!=1) {
		 return "Planet is not deleted";
		}
		else
			return "Planet is deleted";
	   }
	//http://localhost:8080/addPlanet?planetNode=B&planetName=moon
	@RequestMapping(value = "/addPlanet")
	   public String addPlanet(@RequestParam("planetNode") String planetNode,@RequestParam("planetName") String planetName) {
	      
		System.out.println("Planet is added");
		int i= excelReader.addPlanet(planetNode,planetName);
		if(i!=1) {
		 return "Planet is not added";
		}
		else
			return "Planet is added";
	   }
	
	//http://localhost:8080/updatePlanet?planetNode=B&planetName=moon
		@RequestMapping(value = "/updatePlanet")
		   public String updatePlanet(@RequestParam("planetNode") String planetNode,@RequestParam("planetName") String planetName) {
		      
			System.out.println("Planet is updated");
			int i= excelReader.updatePlanet(planetNode,planetName);
			if(i!=1) {
			 return "Planet is not updated";
			}
			else
				return "Planet is updated";
		   }
		
		//http://localhost:8080/getSortestPath?planetSource=B&planetDestination=C
				@RequestMapping(value = "/getSortestPath")
				   public StringBuffer getSortestPath(@RequestParam("planetSource") String planetSource,@RequestParam("planetDestination") String planetDestination) {
				      
					System.out.println("Finding sortest path using Dijekestra Algo");
		//dJ.getPlanetData(planetSource,planetDestination);
					//dJ.getPlanetData();
					return dijkstra.getAllRoutes(planetSource, planetDestination);
					//return "Finding sortest path using Dijekestra Algo";
				   }
	
}
