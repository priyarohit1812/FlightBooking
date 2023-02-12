package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.Airport;

public class AirportDao implements IAirportDao {

	@Override
	public List<Airport> getAll() {
		String hql = "FROM Airport";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<Airport> query = session.createQuery(hql, Airport.class);
		List<Airport> all = query.list();
		session.close();
		return all;
	}

	@Override
	public Airport get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Airport airport = session.get(Airport.class, id);
		session.close();
		return airport;
	}

	@Override
	public boolean insert(Airport airport) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(airport);
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
	public boolean update(Airport airport) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(airport);
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
		String hql = "DELETE FROM Airport a WHERE a.id = :airport_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("airport_id", id);
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
