package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.SeatType;

public interface ISeatTypeDao {
	List<SeatType> getAll();
	SeatType get(int id);
	boolean insert(SeatType seatType);
	boolean update(SeatType seatType);
	boolean delete(int id);
	
}
