package org.simplilearn.fms.service;

import java.util.List;

import org.simplilearn.fms.dao.IUserDao;
import org.simplilearn.fms.dao.UserDao;
import org.simplilearn.fms.entities.User;

public class UserService implements IUserService {
	private IUserDao userDao = new UserDao();

	public UserService() {
		User rootUser = this.userDao.getRootUser();
		if (rootUser == null) {
			this.userDao.insert(new User(0, "Administrator", "admin", "root@fms.com", "0000000000", true, null));
		}
	}

	@Override
	public List<User> getAll() {
		return this.userDao.getAll();
	}

	@Override
	public User get(int id) {
		return this.userDao.get(id);
	}

	@Override
	public boolean save(User user) {
		if (user.getId() > 0) {
			return this.userDao.update(user);
		} else {
			return this.userDao.insert(user);
		}

	}

	@Override
	public boolean delete(int id) {
		return this.userDao.delete(id);
	}

	@Override
	public User loginAsUser(String username, String password) {
		return this.userDao.login(username, password, false);
	}

	@Override
	public User loginAsAdmin(String username, String password) {
		return this.userDao.login(username, password, true);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userDao.getAll(false);
	}

	@Override
	public List<User> getAllAdmins() {
		return this.userDao.getAll(true);
	}

}
