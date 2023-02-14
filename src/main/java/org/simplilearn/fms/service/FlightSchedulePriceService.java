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
	public boolean save(FlightSchedulePrice schedulePrice) {
		if (schedulePrice.getId() > 0) {
			FlightSchedulePrice existingSchedulePrice = get(schedulePrice.getId());
			existingSchedulePrice.setFlightSchedule(schedulePrice.getFlightSchedule());
			existingSchedulePrice.setAvailableSeat(schedulePrice.getAvailableSeat());
			existingSchedulePrice.setPrice(schedulePrice.getPrice());
			existingSchedulePrice.setSeatType(schedulePrice.getSeatType());
			return this.schedulePriceDao.update(existingSchedulePrice);
		} else {
			return this.schedulePriceDao.insert(schedulePrice);
		}
	}

	@Override
	public boolean delete(int id) {
		return this.schedulePriceDao.delete(id);
	}

}
