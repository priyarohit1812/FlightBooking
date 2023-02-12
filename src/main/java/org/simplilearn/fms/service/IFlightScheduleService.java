package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.FlightSchedule;

public interface IFlightScheduleService {
	List<FlightSchedule> getAll();
	FlightSchedule get(int id);
	boolean save(FlightSchedule flightSchedule);
	boolean delete(int id);
}
