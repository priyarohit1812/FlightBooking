package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.Ticket;

public interface ITicketDao {
	List<Ticket> getAll();
	Ticket get(int id);
	boolean insert(Ticket ticket);
	boolean update(Ticket ticket);
	boolean delete(int id);
	
}
