package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.jdbc.ConnectionManager;

public abstract class JdbcTemplate {
	public void executeUpdate(String sql, PreparedStatementSetter psmtSetter) {
		try (
			Connection con = ConnectionManager.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
		){
			psmtSetter.setValues(psmt);
			psmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public Object[] executeQuery(String sql, PreparedStatementSetter psmtSetter, RowMapper<?> mapper) {
		
		try (
			Connection con = ConnectionManager.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
		){
			psmtSetter.setValues(psmt);
			
			ArrayList<Object> objList = new ArrayList<>();
			try (ResultSet rs = psmt.executeQuery();) {
				while (rs.next()) {
					Object obj = mapper.mapRow(rs);
					objList.add(obj);
				}
			}
			return objList.toArray(new Object[objList.size()]);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public Object queryForObject(String sql, PreparedStatementSetter psmtSetter, RowMapper<?> mapper) {
		Object[] objs = executeQuery(sql, psmtSetter, mapper);
		if (objs.length != 1) {
			throw new InvalidResultException();
		}
		
		return objs[0];
	}
}
