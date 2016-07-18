package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
	public void insert(User user) {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		
		JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};
		
		insertJdbcTemplate.executeUpdate(sql);
	}
	
	public void update(User user) throws SQLException {
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
		
		JdbcTemplate updateJdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(4, user.getUserId());
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
			}
		};
		
		updateJdbcTemplate.executeUpdate(sql);
	}
	
	public List<User> findAll() throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS";
		
		SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				User user = new User(
							rs.getString("userId"), 
							rs.getString("password"), 
							rs.getString("name"),
							rs.getString("email"));
				
				return user;
			}
		};
		
		Object[] objs = selectJdbcTemplate.executeQuery(sql);
		List<User> userList = new ArrayList<>();
		for (Object object : objs) {
			userList.add((User) object);
		}
		return userList;
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		
		SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				User user = new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			
				return user;
			}

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		
		Object[] objs = selectJdbcTemplate.executeQuery(sql);
		if (objs.length != 1) {
			return null;
		}
		
		if (!(objs[0] instanceof User)) {
			return null;
		}
		
		return (User) objs[0];
	}
}
