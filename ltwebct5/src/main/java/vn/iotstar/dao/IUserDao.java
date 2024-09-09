package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UsersModel;

public interface IUserDao {

	List<UsersModel> findAll();

	void insert(UsersModel user);

	UsersModel findById(int id);

	boolean CheckEmailExist(String Email);

	boolean CheckUserExist(String User);

	UsersModel findbyUser(String User);

}
