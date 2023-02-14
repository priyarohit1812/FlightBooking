package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.Airline;

public class AirlineDao implements IAirlineDao {

	@Override
	public List<Airline> getAll() {
		String hql = "FROM Airline";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<Airline> query = session.createQuery(hql, Airline.class);
		List<Airline> all = query.list();
		session.close();
		factory.close();
		return all;
	}

	@Override
	public Airline get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Airline airline = session.get(Airline.class, id);
		if (airline != null) {
			Hibernate.initialize(airline.getFlights());
		}
		session.close();
		factory.close();
		return airline;
	}

	@Override
	public boolean insert(Airline airline) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(airline);
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
	public boolean update(Airline airline) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(airline);
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
		String hql = "DELETE FROM Airline a WHERE a.id = :airline_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("airline_id", id);
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
