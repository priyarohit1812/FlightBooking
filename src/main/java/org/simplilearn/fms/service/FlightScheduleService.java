package org.simplilearn.fms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.simplilearn.fms.dao.FlightScheduleDao;
import org.simplilearn.fms.dao.IFlightScheduleDao;
import org.simplilearn.fms.entities.FlightSchedule;
import org.simplilearn.fms.entities.FlightSchedulePrice;

public class FlightScheduleService implements IFlightScheduleService {
	private IFlightScheduleDao flightScheduleDao = new FlightScheduleDao();
	@Override
	public List<FlightSchedule> getAll() {
		return this.flightScheduleDao.getAll();
	}

	@Override
	public FlightSchedule get(int id) {
		return this.flightScheduleDao.get(id);
	}

	@Override
	public boolean save(FlightSchedule flightSchedule) {
		if (flightSchedule.getId() > 0) {
			FlightSchedule existingFlightSchedule = get(flightSchedule.getId());
			existingFlightSchedule.setFlight(flightSchedule.getFlight());
			existingFlightSchedule.setSource(flightSchedule.getSource());
			existingFlightSchedule.setDestination(flightSchedule.getDestination());
			existingFlightSchedule.setDeparture(flightSchedule.getDeparture());
			existingFlightSchedule.setArrival(flightSchedule.getArrival());
			return this.flightScheduleDao.update(existingFlightSchedule);
		} else {
			return this.flightScheduleDao.insert(flightSchedule);
		}

	}

	
	@Override
	public boolean delete(int id) {
		return this.flightScheduleDao.delete(id);
	}

	@Override
	public List<FlightSchedulePrice> search(int sourceId, int destinationId, Timestamp departure) {
		List<FlightSchedulePrice> flightSchedulePrices = new ArrayList<>();
		List<FlightSchedule> flightSchedules = this.flightScheduleDao.search(sourceId, destinationId, departure);
		for (FlightSchedule flightSchedule : flightSchedules) {
			flightSchedulePrices.addAll(flightSchedule.getFlightSchedulePrice());
		}
		return flightSchedulePrices;
	}

}
