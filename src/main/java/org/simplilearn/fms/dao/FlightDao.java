package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.Flight;

public class FlightDao implements IFlightDao {

	@Override
	public List<Flight> getAll() {
		String hql = "FROM Flight";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<Flight> query = session.createQuery(hql, Flight.class);
		List<Flight> all = query.list();
		session.close();
		return all;
	}

	@Override
	public Flight get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Flight flight = session.get(Flight.class, id);
		if (flight != null) {
			Hibernate.initialize(flight.getFlightSchedule());
		}
		session.close();
		return flight;
	}

	@Override
	public boolean insert(Flight flight) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(flight);
			tx.commit();
			isInserted = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return isInserted;
	}

	@Override
	public boolean update(Flight flight) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(flight);
			tx.commit();
			isUpdated = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return isUpdated;
	}

	@Override
	public boolean delete(int id) {
		String hql = "DELETE FROM Flight f WHERE f.id = :flight_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("flight_id", id);
			deleteQuery.executeUpdate();
			tx.commit();
			isDeleted = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return isDeleted;
	}

}
