package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.AirlineDao;
import org.simplilearn.fms.dao.IAirlineDao;
import org.simplilearn.fms.entities.Airline;

public class AirlineService implements IAirlineService {
	private IAirlineDao airlineDao = new AirlineDao();

	@Override
	public List<Airline> getAll() {
		return this.airlineDao.getAll();
	}

	@Override
	public Airline get(int id) {
		return this.airlineDao.get(id);
	}

	@Override
	public boolean save(Airline airline) {
		if (airline.getId() > 0) {
			return this.airlineDao.update(airline);
		} else {
			return this.airlineDao.insert(airline);
		}
	}

	@Override
	public boolean delete(int id) {
		return this.airlineDao.delete(id);
	}
}
