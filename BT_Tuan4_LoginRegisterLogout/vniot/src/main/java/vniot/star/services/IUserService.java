package vniot.star.services;

import vniot.star.models.UserModel;

public interface IUserService {

	 UserModel login(String username, String password);
	 
	 UserModel findByUsername(String username);
	 

}
