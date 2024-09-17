package vniot.star.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vniot.star.configs.DBConnectMySQL;
import vniot.star.dao.IUserDao;
import vniot.star.models.UserModel;


public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	public List<UserModel> findAll() {
		String sql ="select * from users";
		List<UserModel> list = new ArrayList<>(); 
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next() /*Next từng DÒNG tới cuối bảng*/) {
                list.add(new UserModel(
                		rs.getInt("id"), 
                		rs.getString("username"), 
                		rs.getString("password"), 
                		rs.getString("email"), 
                		rs.getString("phone"),
                		rs.getString("images"),
                		rs.getString("fullname"),
                		rs.getInt("roleid"),
                		rs.getDate("createdate")        		                		
                		));
            }
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	
	public UserModel findOne(int id) {
		String sql = "SELECT * FROM users WHERE id = ? ";
		
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setImages(rs.getString("images"));
				user.setEmail(rs.getString("email"));
				user.setRoleid(rs.getInt("roleid"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	 public boolean checkExistEmail(String email) {
		 boolean duplicate = false;
		 String query = "select * from [login] where email = ?";
		 try {
		 conn = super.getDatabaseConnection();
		 ps = conn.prepareStatement(query);
		 ps.setString(1, email);
		 rs = ps.executeQuery();
		 if (rs.next()) {
		 duplicate = true;
		 }
		 ps.close();
		 conn.close();
		 } catch (Exception ex) {}
		 return duplicate;
		 }
	
	
	public UserModel findByUsername(String username) {
		String sql = "SELECT * FROM login WHERE username = ? ";
		
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setImages(rs.getString("images"));
				user.setEmail(rs.getString("email"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreatedate(rs.getDate("createdate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void insert(UserModel user) {
		 String sql = "INSERT INTO login (id, username, password, images, fullname, roleid,phone, createdate) VALUES (?,?,?,?,?,?,?,?)";
		 try {
			 conn = super.getDatabaseConnection();
			 ps = conn.prepareStatement(sql);
			 	
			 ps.setString(1, user.getUsername());
			 ps.setString(2, user.getPassword());
			 ps.setString(3, user.getImages());
			 ps.setString(4, user.getFullname());
			 ps.setInt(5, user.getRoleid());
			 ps.setString(6,user.getPhone());
			 ps.setDate(7,user.getCreatedate());
			 ps.executeUpdate();
			 
		 } catch (Exception e) {
			 e.printStackTrace();}
	}
		
	
	 public boolean checkExistUsername(String username) {
		 boolean duplicate = false;
		 String query = "select * from login where username = ?";
		 try {
		 conn = super.getDatabaseConnection();
		 ps = conn.prepareStatement(query);
		 ps.setString(1, username);
		 rs = ps.executeQuery();
		 if (rs.next()) {
		 duplicate = true;
		 }
		 ps.close();
		 conn.close();
		 } catch (Exception ex) {}
		 return duplicate;
		 }

	
	 public static void main(String[] args) {
		 	
	        IUserDao userDao = new UserDaoImpl();
	        System.out.println(userDao.findByUsername("huy"));
//	        userDao.insertregister(new UserModel("huy","123","qhuy","1"));
//	        System.out.println(userDao.findByUsername("huy"));
//	       userDao.insert(new UserModel("huy","123",null,"quang huy","huy@", 1,"0123",null));
	        
//	        List<UserModel> list = userDao.findAll();
//	        for (UserModel user : list) {
//	            System.out.println(user);
//	        }
//	    }
	 }
	 
	 public void insertregister(UserModel user) {
		 String sql = "Insert INTO login(fullname,password,username,email) Values(?,?,?,?)";
		 try {
			 conn = super.getDatabaseConnection();
			 ps = conn.prepareStatement(sql);
			 ps.setString(4, user.getFullname());
			 ps.setString(2, user.getPassword());
			 ps.setString(3, user.getUsername());
			 ps.setString(1, user.getEmail());
			 ps.executeUpdate();
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	 }

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		IUserDao userDao = new UserDaoImpl();
		if (userDao.checkExistEmail(email)) {
			return false;
		}
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		userDao.insertregister(new UserModel(email, password, username, fullname));
		return true;
	}



	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean CheckEmailDuplicate(String email, String email2) {
		boolean duplicate = false;
		if (email.equals(email2)) {
			duplicate = true;
		}
		return duplicate;
	}


	public void UpdatePassword(String username, String password) {
		String updateSQL = "UPDATE login SET password = ? WHERE username = ?";
		try {
			conn = super.getDatabaseConnection();
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            // Thiết lập các giá trị cần cập nhật
            ps.setString(1, password);  // Cập nhật password
            ps.setString(2, username); // Đặt giá trị cho trường "user"

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	
}
