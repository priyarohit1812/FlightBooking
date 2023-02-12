package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.FlightSchedulePriceDao;
import org.simplilearn.fms.dao.IFlightSchedulePriceDao;
import org.simplilearn.fms.entities.FlightSchedulePrice;

public class FlightSchedulePriceService implements IFlightSchedulePriceService {
	private IFlightSchedulePriceDao schedulePriceDao = new FlightSchedulePriceDao();
	@Override
	public List<FlightSchedulePrice> getAll() {
		return this.schedulePriceDao.getAll();
	}

	@Override
	public FlightSchedulePrice get(int id) {
		return this.schedulePriceDao.get(id);
	}

	@Override
	public boolean insert(FlightSchedulePrice schedulePrice) {
		return this.schedulePriceDao.insert(schedulePrice);
	}

	@Override
	public boolean update(FlightSchedulePrice schedulePrice) {
		return this.schedulePriceDao.update(schedulePrice);
	}

	@Override
	public boolean delete(int id) {
		return this.schedulePriceDao.delete(id);
	}

}
