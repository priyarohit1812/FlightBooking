package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.Ticket;

public class TicketDao implements ITicketDao {

	@Override
	public List<Ticket> getAll() {
		String hql = "FROM Ticket";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<Ticket> query = session.createQuery(hql, Ticket.class);
		List<Ticket> all = query.list();
		session.close();
		factory.close();
		return all;
	}

	@Override
	public Ticket get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Ticket ticket = session.get(Ticket.class, id);
		session.close();
		factory.close();
		return ticket;
	}

	@Override
	public boolean insert(Ticket ticket) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(ticket);
			tx.commit();
			isInserted = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
		return isInserted;
	}

	@Override
	public boolean update(Ticket ticket) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(ticket);
			tx.commit();
			isUpdated = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
		return isUpdated;
	}

	@Override
	public boolean delete(int id) {
		String hql = "DELETE FROM Ticket t WHERE t.id = :ticket_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("ticket_id", id);
			deleteQuery.executeUpdate();
			tx.commit();
			isDeleted = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
		return isDeleted;
	}

}
