package org.simplilearn.fms.service;

import java.sql.Timestamp;
import java.util.List;

import org.simplilearn.fms.entities.FlightSchedule;
import org.simplilearn.fms.entities.FlightSchedulePrice;

public interface IFlightScheduleService {
	List<FlightSchedule> getAll();
	FlightSchedule get(int id);
	boolean save(FlightSchedule flightSchedule);
	boolean delete(int id);
	List<FlightSchedulePrice> search(int sourceId, int destinationId, Timestamp departure);
}
