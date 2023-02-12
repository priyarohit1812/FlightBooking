package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.SeatType;

public interface ISeatTypeService {
	List<SeatType> getAll();

	SeatType get(int id);

	boolean save(SeatType seatType);

	boolean delete(int id);
}
