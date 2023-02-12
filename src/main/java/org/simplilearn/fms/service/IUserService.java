package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.entities.User;

public interface IUserService {
	List<User> getAll();
	List<User> getAllUsers();
	List<User> getAllAdmins();
	User get(int id);
	boolean save(User user);
	boolean delete(int id);
	User loginAsUser(String username,String password);
	User loginAsAdmin(String username,String password);
}
