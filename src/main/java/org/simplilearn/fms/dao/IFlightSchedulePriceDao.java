package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.FlightSchedulePrice;

public interface IFlightSchedulePriceDao {
	List<FlightSchedulePrice> getAll();
	FlightSchedulePrice get(int id);
	boolean insert(FlightSchedulePrice schedulePrice);
	boolean update(FlightSchedulePrice schedulePrice);
	boolean delete(int id);
	
}
