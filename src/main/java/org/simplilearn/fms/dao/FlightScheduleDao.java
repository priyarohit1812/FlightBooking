package org.simplilearn.fms.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.FlightSchedule;

public class FlightScheduleDao implements IFlightScheduleDao {

	@Override
	public List<FlightSchedule> getAll() {
		String hql = "FROM FlightSchedule";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<FlightSchedule> query = session.createQuery(hql, FlightSchedule.class);
		List<FlightSchedule> all = query.list();
		session.close();
		factory.close();
		return all;
	}

	@Override
	public FlightSchedule get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		FlightSchedule flightSchedule = session.get(FlightSchedule.class, id);
		if (flightSchedule != null) {
			Hibernate.initialize(flightSchedule.getFlightSchedulePrice());
		}
		session.close();
		factory.close();
		return flightSchedule;
	}

	@Override
	public boolean insert(FlightSchedule flightSchedule) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(flightSchedule);
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
	public boolean update(FlightSchedule flightSchedule) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(flightSchedule);
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
		String hql = "DELETE FROM FlightSchedule f WHERE f.id = :flightSchedule_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("flightSchedule_id", id);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<FlightSchedule> search(int sourceId, int destinationId, Timestamp departure) {
		String sql = "SELECT * FROM fms_flight_schedule fs WHERE fs.source_id = :source_id AND fs.destination_id = :destination_id AND departure >= :departure";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		NativeQuery<FlightSchedule> query = session.createSQLQuery(sql);
		query.addEntity(FlightSchedule.class);
		query.setParameter("source_id", sourceId);
		query.setParameter("destination_id", destinationId);
		query.setParameter("departure", departure);
		List<FlightSchedule> all = query.list();
		for (FlightSchedule flightSchedule : all) {
			Hibernate.initialize(flightSchedule.getFlightSchedulePrice());
		}
		session.close();
		factory.close();
		return all;
	}

}
