package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.FlightSchedulePrice;

public class FlightSchedulePriceDao implements IFlightSchedulePriceDao {

	@Override
	public List<FlightSchedulePrice> getAll() {
		String hql = "FROM FlightSchedulePrice";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<FlightSchedulePrice> query = session.createQuery(hql, FlightSchedulePrice.class);
		List<FlightSchedulePrice> all = query.list();
		session.close();
		return all;
	}

	@Override
	public FlightSchedulePrice get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		FlightSchedulePrice schedulePrice = session.get(FlightSchedulePrice.class, id);
		if (schedulePrice != null) {
			Hibernate.initialize(schedulePrice.getSeatType());
		}
		session.close();
		return schedulePrice;
	}

	@Override
	public boolean insert(FlightSchedulePrice schedulePrice) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(schedulePrice);
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
	public boolean update(FlightSchedulePrice schedulePrice) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(schedulePrice);
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
		String hql = "DELETE FROM FlightSchedulePrice f WHERE f.id = :schedulePrice_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("schedulePrice_id", id);
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
