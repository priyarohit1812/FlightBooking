package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.Airport;

public interface IAirportService {
	List<Airport> getAll();
	Airport get(int id);
	boolean save(Airport airport);
	boolean delete(int id);
	
}
