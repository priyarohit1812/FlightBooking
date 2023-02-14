package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.FlightSchedulePrice;

public interface IFlightSchedulePriceService {
	List<FlightSchedulePrice> getAll();
	FlightSchedulePrice get(int id);
	boolean save(FlightSchedulePrice schedulePrice);
	boolean delete(int id);
}
