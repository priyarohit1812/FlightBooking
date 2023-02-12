package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.FlightScheduleDao;
import org.simplilearn.fms.dao.IFlightScheduleDao;
import org.simplilearn.fms.entities.FlightSchedule;

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
			return this.flightScheduleDao.update(flightSchedule);
		} else {
			return this.flightScheduleDao.insert(flightSchedule);
		}

	}

	
	@Override
	public boolean delete(int id) {
		return this.flightScheduleDao.delete(id);
	}

}
