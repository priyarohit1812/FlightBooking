package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.Airport;

public interface IAirportDao {
	List<Airport> getAll();
	Airport get(int id);
	boolean insert(Airport airport);
	boolean update(Airport airport);
	boolean delete(int id);
	
}
