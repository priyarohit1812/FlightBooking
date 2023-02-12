package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.AirportDao;
import org.simplilearn.fms.dao.IAirportDao;
import org.simplilearn.fms.entities.Airport;

public class AirportService implements IAirportService {
	private IAirportDao airportDao = new AirportDao();
	@Override
	public List<Airport> getAll() {
		return this.airportDao.getAll();
	}

	@Override
	public Airport get(int id) {
		return this.airportDao.get(id);
	}

	@Override
	public boolean save(Airport airport) {
		if (airport.getId()>0) {
			return this.airportDao.update(airport);
		} else {
			return this.airportDao.insert(airport);
		}
	}

	
	@Override
	public boolean delete(int id) {
		return this.airportDao.delete(id);
	}

}
