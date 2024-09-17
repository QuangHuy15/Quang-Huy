package vniot.star.dao;

import java.util.List;

import vniot.star.models.UserModel;

public interface IUserDao {
	
	List<UserModel> findAll();
	
	UserModel findOne(int id);
	
	UserModel findByUsername(String username);
	
	void insert(UserModel user);
	
	boolean register(String email, String password, String username, String fullname, String phone);
	 
	boolean checkExistEmail(String email);
	 
	boolean checkExistUsername(String username);
			  
	boolean checkExistPhone(String phone);
	
	void insertregister(UserModel user);

}
