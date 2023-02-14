package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.SeatType;

public class SeatTypeDao implements ISeatTypeDao {

	@Override
	public List<SeatType> getAll() {
		String hql = "FROM SeatType";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<SeatType> query = session.createQuery(hql, SeatType.class);
		List<SeatType> all = query.list();
		session.close();
		factory.close();
		return all;
	}

	@Override
	public SeatType get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		SeatType seatType = session.get(SeatType.class, id);
		session.close();
		factory.close();
		return seatType;
	}

	@Override
	public boolean insert(SeatType seatType) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(seatType);
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
	public boolean update(SeatType seatType) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(seatType);
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
		String hql = "DELETE FROM SeatType s WHERE s.id = :seatType_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("seatType_id", id);
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
