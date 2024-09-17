package vniot.star.services.impl;

import vniot.star.dao.IUserDao;
import vniot.star.dao.impl.UserDaoImpl;
import vniot.star.models.UserModel;
import vniot.star.services.IUserService;

public class UserServiceImpl implements IUserService{
	
	IUserDao userDao = new UserDaoImpl();

	public UserModel login(String username, String password) {
		
		UserModel user = this.findByUsername(username);
		 
		if (user != null && password.equals(user.getPassword())) {
			 return user;
		 }
		 return null;
	}
	
	public UserModel findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}
	
	public boolean register(String username, String password, String email, String fullname, String phone ) {
			 if (userDao.checkExistUsername(username)) {
			 return false;
			 }
			 long millis=System.currentTimeMillis();   
			java.sql.Date date=new java.sql.Date(millis);
			 userDao.insert(new UserModel(email, username, fullname,password));
			 return true;
			 }
	
	public boolean checkExistEmail(String email) {
		 return userDao.checkExistEmail(email);
		 }
	
	public boolean checkExistUsername(String username) {
		 return userDao.checkExistUsername(username);
		 }
		 
	public boolean checkExistPhone(String phone) {
		 return userDao.checkExistPhone(phone);
		 }
		 
	public void insert(UserModel user) {
		 userDao.insert(user);
		 }
	
	public static void main(String[] args) {
	 	
        IUserService userDao = new UserServiceImpl();
        System.out.println(userDao.findByUsername("huy"));
       
       //List<UserModel> list = userDao.findAll();
       // for (UserModel user : list) {
       //     System.out.println(user);
       // }
    }
}
