package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.ITicketDao;
import org.simplilearn.fms.dao.TicketDao;
import org.simplilearn.fms.entities.Ticket;

public class TicketService implements ITicketService {
	private ITicketDao ticketDao = new TicketDao();
	@Override
	public List<Ticket> getAll() {
		return this.ticketDao.getAll();
	}

	@Override
	public Ticket get(int id) {
		return this.ticketDao.get(id);
	}

	@Override
	public boolean insert(Ticket ticket) {
		return this.ticketDao.insert(ticket);
	}

	@Override
	public boolean update(Ticket ticket) {
		return this.ticketDao.update(ticket);
	}

	@Override
	public boolean delete(int id) {
		return this.ticketDao.delete(id);
	}

}
