package com.planet.h2repository.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class PlanetJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insert(PlanetNames planetNames) {
		return jdbcTemplate.update("insert into planet (planetNode,planetName) " + "values(?,  ?)",
				new Object[] { planetNames.getPlanetNode(), planetNames.getPlanetName()});
	}
	public int insertIntoRoutes(Routes routes) {
		return jdbcTemplate.update("insert into routes (routeId,planetOrigin,planetDestination,distance) " + "values(?,  ?, ?, ?)",
				new Object[] { routes.getRouteId(), routes.getPlanetOrigin(),routes.getPlanetDestination(),routes.getDistance()});
	}
	
	class PlanetNamesRowMapper implements RowMapper<PlanetNames> {
		@Override
		public PlanetNames mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlanetNames planet = new PlanetNames();
			planet.setPlanetNode(rs.getString("planetNode"));
			planet.setPlanetName(rs.getString("planetName"));
			return planet;
		}

	}

	public List<PlanetNames> findAll() {
		return jdbcTemplate.query("select * from planet", new PlanetNamesRowMapper());
	}
	
	class RoutesRowMapper implements RowMapper<Routes> {
		@Override
		public Routes mapRow(ResultSet rs, int rowNum) throws SQLException {
			Routes routes = new Routes();
			routes.setRouteId(rs.getInt("routeId"));
			routes.setPlanetOrigin(rs.getString("planetOrigin"));
			routes.setPlanetDestination(rs.getString("planetDestination"));
			routes.setDistance(rs.getInt("distance"));
			return routes;
		}

	}

	public List<Routes> findAllRoutes() {
		return jdbcTemplate.query("select * from routes", new RoutesRowMapper());
	}
	
	public PlanetNames getPlanetByNode(String planetNode) {
		return jdbcTemplate.queryForObject("select * from planet where planetNode=?", new Object[] { planetNode },
				new BeanPropertyRowMapper<PlanetNames>(PlanetNames.class));
	}
	public int deleteByPlanetNode(String planetNode) {
		return jdbcTemplate.update("delete from planet where planetNode=?", new Object[] { planetNode });
		
	}
	public int addPlanet(String planetNode, String planetName) {
		return jdbcTemplate.update("insert into planet (planetNode,planetName) " + "values(?,  ?)",
				new Object[] { planetNode, planetName});
	}
	public int updatePlanet(String planetNode, String planetName) {
		return jdbcTemplate.update("update planet " + " set planetName = ? " + " where planetNode = ?",
				new Object[] { planetName,planetNode });
	}
}
