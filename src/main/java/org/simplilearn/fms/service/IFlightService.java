package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.Flight;

public interface IFlightService {
	List<Flight> getAll();
	Flight get(int id);
	boolean save(Flight flight);
	boolean delete(int id);
	
}
