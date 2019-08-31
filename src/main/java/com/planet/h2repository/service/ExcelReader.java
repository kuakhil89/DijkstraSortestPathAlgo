package com.planet.h2repository.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planet.h2repository.repository.PlanetJdbcRepository;
import com.planet.h2repository.repository.PlanetNames;
import com.planet.h2repository.repository.Routes;
@Service
public class ExcelReader {
	

    //public  final String SAMPLE_XLSX_FILE_PATH = "d:/assignment1.xlsx";
    @Autowired
    PlanetJdbcRepository planetJdbcRepository;
    

    public  void read() throws IOException, InvalidFormatException {
    	
    	
    	List<String> SAMPLE_XLSX_FILE_PATH = new ArrayList<String>();
    	SAMPLE_XLSX_FILE_PATH.add("g:/assignment/assignment1.xlsx");
    	//SAMPLE_XLSX_FILE_PATH.add("d:/assignment2.xlsx");
    	for (String string : SAMPLE_XLSX_FILE_PATH) {
			
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(string));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        // you can use a Java 8 forEach with lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });
       

        // Getting the Sheet at index zero
        //Sheet sheet = workbook.getSheetAt(0);
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        


        //You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
				/*
				 * Iterator<Row> rowIterator = sheet.rowIterator(); while
				 * (rowIterator.hasNext()) { Row row = rowIterator.next();
				 * 
				 * // Now let's iterate over the columns of the current row Iterator<Cell>
				 * cellIterator = row.cellIterator();
				 * 
				 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next();
				 * System.out.print(cell + "\t"); } System.out.println(); }
				 */
        //=============================================
        switch (sheet.getSheetName()) {
        case "Planet Names":
        	List<PlanetNames> planetNameList=new ArrayList<PlanetNames>();
        	
        	for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
        		PlanetNames p=new PlanetNames(); 
                Row ro=sheet.getRow(i);
                for(int j=ro.getFirstCellNum();j<=ro.getLastCellNum();j++){
                    Cell ce = ro.getCell(j);
                  if(j==0){  
                      //If you have Header in text It'll throw exception because it won't get NumericValue
                      p.setPlanetNode(ce.getStringCellValue());
                  }
                  if(j==1){
                      p.setPlanetName(ce.getStringCellValue());
                  }  
                }
                planetJdbcRepository.insert(p);
                planetNameList.add(p);
            }
        	System.out.println(planetNameList);
        	break;
        case "Routes":
        	List<Routes> routeList=new ArrayList<Routes>();
        	
        	for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
        		Routes r=new Routes(); 
                Row ro=sheet.getRow(i);
                for(int j=ro.getFirstCellNum();j<=ro.getLastCellNum();j++){
                    Cell ce = ro.getCell(j);
                  if(j==0){  
                      //If you have Header in text It'll throw exception because it won't get NumericValue
                      r.setRouteId((int)ce.getNumericCellValue());
                  }
                  if(j==1){
                      r.setPlanetOrigin(ce.getStringCellValue());
                  }  
                  if(j==2){
                      r.setPlanetDestination(ce.getStringCellValue());
                  } 
                  if(j==3){
                      r.setDistance(ce.getNumericCellValue());
                  } 
                }
                planetJdbcRepository.insertIntoRoutes(r);
                routeList.add(r);
            }
        	System.out.println(routeList);
        	break;
        }
        
        });
	
        workbook.close();
    }
    }


	public List<PlanetNames> getPlanetData() {
		System.out.println("Right now in service now calling repository");
		List<PlanetNames> planet=planetJdbcRepository.findAll();
		if(null!=planet && !planet.isEmpty())
		for(PlanetNames planetNames : planet) {
			System.out.println(planetNames.getPlanetNode()+","+planetNames.getPlanetName());
		}
		return planet;
		
	}


	public PlanetNames getPlanetByNode(String planetNode) {
		return planetJdbcRepository.getPlanetByNode(planetNode);
	}


	public int deleteByPlanetNode(String planetNode) {
		return planetJdbcRepository.deleteByPlanetNode(planetNode);	
	}


	public int addPlanet(String planetNode, String planetName) {
		List<PlanetNames> p=planetJdbcRepository.findAll();
		List<String> s1=new ArrayList<String>();
		for (PlanetNames planetNames : p) {
			s1.add(planetNames.getPlanetNode());
		}
			if(!(s1.contains(planetNode)))
			{ 
				return planetJdbcRepository.addPlanet(planetNode,planetName);
			}
			
		return 0;
	}


	public int updatePlanet(String planetNode, String planetName) {
		return planetJdbcRepository.updatePlanet(planetNode,planetName);
	}

}
