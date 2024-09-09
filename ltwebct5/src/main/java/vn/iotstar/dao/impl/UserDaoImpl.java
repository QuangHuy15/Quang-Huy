package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UsersModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public PreparedStatement findMissingIdStmt = null;

	@Override
	public List<UsersModel> findAll() {
		String sql = "select * from users";

		List<UsersModel> list = new ArrayList<>();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new UsersModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("image")));
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public UsersModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new UsersModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
//	public void insert(UsersModel user) {
//		
//		String sql = "INSERT INTO users(id, username, email, password, fullname, image) VALUES (?, ?, ?, ?, ?, ?)";
//		
//		try {
//			conn = super.getDatabaseConnection();
//			ps = conn.prepareStatement(sql);
//			
//			ps.setInt(1,user.getId());
//			ps.setString(2,user.getUsername());
//			ps.setString(3,user.getEmail());
//			ps.setString(4,user.getPassword());
//			ps.setString(5,user.getFullname());
//			ps.setString(6,user.getImages());
//			
//			ps.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	public void insert(UsersModel user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO users(id,username,email,password,fullname,image) VALUES (?,?,?,?,?,?)";

		try {
			conn = super.getDatabaseConnection();

			// Tìm và chèn dữ liệu vào ID trống
			String findMissingIdSQL = "SELECT COALESCE(MIN(t1.id + 1), 1) AS missing_id " + "FROM users t1 "
					+ "LEFT JOIN users t2 ON t1.id + 1 = t2.id " + "WHERE t2.id IS NULL";
			findMissingIdStmt = conn.prepareStatement(findMissingIdSQL);

			ps = conn.prepareStatement(sql);
			rs = findMissingIdStmt.executeQuery();

			while (rs.next()) {
				int missingId = rs.getInt("missing_id");

				// Chèn thông tin vào ID trống
				ps.setInt(1, missingId);
				ps.setString(2, user.getUsername());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getFullname());
				ps.setString(6, user.getImages());

				ps.executeUpdate();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean CheckEmailExist(String Email) {
		// TODO Auto-generated method stub
		boolean duplicate = false;
		String query = "select * from users WHERE email = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, Email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	public boolean CheckUserExist(String User) {
		// TODO Auto-generated method stub
		boolean duplicate = false;
		String query = "select * from users WHERE username = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, User);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	public UsersModel findbyUser(String User) {
		// TODO Auto-generated method stub
		String sql = "select * from users WHERE username = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, User);
			rs = ps.executeQuery();

			while (rs.next()) {
				return new UsersModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("image"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		UserDaoImpl userDao = new UserDaoImpl();
//		userDao.insert(new UsersModel("Huy","Huy@gmail.com", "1111","QHuy", "null"));
//		userDao.insert(new UsersModel(0, "Bao", "Huy@gmail.com", "1","Quang Huy","null"));

//		UsersModel user=userDao.findbyUser("Huy");

//		for (UsersModel user : list) {
//		System.out.println(user);
//	}

//		UsersModel user1 = userDao.findById(1);
//		if (user1 != null) {
//		    System.out.println(user1);
//		} else {
//		    System.out.println("User not found!");
//		}

//		UsersModel userid = userDao.findById(7);
//		System.out.println(userid);

		List<UsersModel> list = userDao.findAll();

		for (UsersModel user : list) {
			System.out.println(user);
		}
	}
}
