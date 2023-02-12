package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.Airline;

public interface IAirlineDao {
	List<Airline> getAll();
	Airline get(int id);
	boolean insert(Airline airline);
	boolean update(Airline airline);
	boolean delete(int id);
}
