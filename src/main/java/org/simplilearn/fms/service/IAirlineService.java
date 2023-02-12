package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.Airline;

public interface IAirlineService {
	List<Airline> getAll();
	Airline get(int id);
	boolean save(Airline airline);
	boolean delete(int id);
}
