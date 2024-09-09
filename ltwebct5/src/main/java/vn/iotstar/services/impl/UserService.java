package vn.iotstar.services.impl;

import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UsersModel;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService {

	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		UsersModel user = userDao.findbyUser(username);
		if (user == null) {
			return "No account information matches with username. Check again!";
		} else if (password.equals(user.getPassword())) {

			return "Welcome " + user.getFullname();
		}
		return "Password incorrect.Check again!";
	}

	@Override
	public UsersModel get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
