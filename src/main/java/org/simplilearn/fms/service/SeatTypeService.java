package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.ISeatTypeDao;
import org.simplilearn.fms.dao.SeatTypeDao;
import org.simplilearn.fms.entities.SeatType;

public class SeatTypeService implements ISeatTypeService {
	private ISeatTypeDao seatTypeDao = new SeatTypeDao();

	public SeatTypeService() {
		List<SeatType> lstSeatTypes = this.seatTypeDao.getAll();
		if (lstSeatTypes == null || lstSeatTypes.isEmpty()) {
			// Insert Seat Type General Class
			this.seatTypeDao.insert(new SeatType(0, "GEN", "General Class"));
			// Insert Seat Type Business Class
			this.seatTypeDao.insert(new SeatType(0, "BUS", "Business Class"));
		}
	}

	@Override
	public List<SeatType> getAll() {
		return this.seatTypeDao.getAll();
	}

	@Override
	public SeatType get(int id) {
		return this.seatTypeDao.get(id);
	}

	@Override
	public boolean save(SeatType seatType) {
		if (seatType.getId() > 0) {
			return this.seatTypeDao.update(seatType);
		} else {
			return this.seatTypeDao.insert(seatType);
		}
	}

	@Override
	public boolean delete(int id) {
		return this.seatTypeDao.delete(id);
	}

}
