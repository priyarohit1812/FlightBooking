package org.simplilearn.fms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.simplilearn.fms.config.HibConfig;
import org.simplilearn.fms.entities.User;

public class UserDao implements IUserDao {

	@Override
	public List<User> getAll() {
		String hql = "FROM User";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<User> query = session.createQuery(hql, User.class);
		List<User> all = query.list();
		session.close();
		return all;
	}

	@Override
	public User get(int id) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		User user = session.get(User.class, id);
		if (user != null) {
			Hibernate.initialize(user.getTickets());
		}
		session.close();
		return user;
	}

	@Override
	public boolean insert(User user) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isInserted = false;
		try {
			tx = session.beginTransaction();
			session.save(user);
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
	public boolean update(User user) {
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = session.beginTransaction();
			session.merge(user);
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
		String hql = "DELETE FROM User u WHERE u.id = :user_id";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = session.beginTransaction();
			Query<?> deleteQuery = session.createQuery(hql);
			deleteQuery.setParameter("user_id", id);
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

	@Override
	public User login(String usrename, String password, boolean isAdmin) {
		String hql = "FROM User u WHERE (u.email = :username OR u.mobile = :username) AND u.password = :password AND u.isAdmin = :isAdmin";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("username", usrename);
		query.setParameter("password", password);
		query.setParameter("isAdmin", isAdmin);
		User user = query.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public List<User> getAll(boolean isAdmin) {
		String hql = "FROM User u WHERE u.isAdmin = :isAdmin";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("isAdmin", isAdmin);
		List<User> all = query.list();
		session.close();
		return all;
	}

	@Override
	public User getRootUser() {
		String hql = "FROM User u WHERE u.email = :rootEmail AND u.isAdmin = true";
		SessionFactory factory = HibConfig.getSessionFactory();
		Session session = factory.openSession();
		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("rootEmail", "root@fms.com");
		User user = query.uniqueResult();
		session.close();
		return user;
	}

}
