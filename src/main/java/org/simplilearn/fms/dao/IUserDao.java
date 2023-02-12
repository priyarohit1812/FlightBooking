package org.simplilearn.fms.dao;

import java.util.List;

import org.simplilearn.fms.entities.User;

public interface IUserDao {
	List<User> getAll();
	List<User> getAll(boolean isAdmin);
	User get(int id);
	User getRootUser();
	boolean insert(User user);
	boolean update(User user);
	boolean delete(int id);
	User login(String usrename, String password, boolean isAdmin);
}
