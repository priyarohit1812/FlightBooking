package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.FlightSchedule;

public interface IFlightScheduleDao {
	List<FlightSchedule> getAll();
	FlightSchedule get(int id);
	boolean insert(FlightSchedule flightSchedule);
	boolean update(FlightSchedule flightSchedule);
	boolean delete(int id);
	
}
