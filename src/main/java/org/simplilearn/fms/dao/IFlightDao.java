package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.Flight;

public interface IFlightDao {
	List<Flight> getAll();
	Flight get(int id);
	boolean insert(Flight flight);
	boolean update(Flight flight);
	boolean delete(int id);
	
}
