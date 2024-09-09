package vn.iotstar.services;

import vn.iotstar.models.UsersModel;

public interface IUserService {

	String login(String username, String password);

	UsersModel get(String username);

}
