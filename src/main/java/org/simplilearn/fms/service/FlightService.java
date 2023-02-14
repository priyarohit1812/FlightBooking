package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.FlightDao;
import org.simplilearn.fms.dao.IFlightDao;
import org.simplilearn.fms.entities.Flight;

public class FlightService implements IFlightService {
	private IFlightDao flightDao = new FlightDao();
	@Override
	public List<Flight> getAll() {
		return this.flightDao.getAll();
	}

	@Override
	public Flight get(int id) {
		return flightDao.get(id);
	}

	@Override
	public boolean save(Flight flight) {
		if (flight.getId() > 0) {
			Flight existingFlight = get(flight.getId());
			existingFlight.setModel(flight.getModel());
			existingFlight.setAirline(flight.getAirline());
			return this.flightDao.update(existingFlight);
		} else {
			return this.flightDao.insert(flight);
		}		
	}

	@Override
	public boolean delete(int id) {
		return this.flightDao.delete(id);
	}

}
