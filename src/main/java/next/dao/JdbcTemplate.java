package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.jdbc.ConnectionManager;

public abstract class JdbcTemplate {
	public void executeUpdate(String sql, PreparedStatementSetter psmtSetter) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			psmtSetter.setValues(pstmt);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
	
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public Object[] executeQuery(String sql, PreparedStatementSetter psmtSetter, RowMapper mapper) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Object> objList = new ArrayList<>();
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			psmtSetter.setValues(pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Object obj = mapper.mapRow(rs);
				objList.add(obj);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
	
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return objList.toArray(new Object[objList.size()]);
	}
	
	public Object queryForObject(String sql, PreparedStatementSetter psmtSetter, RowMapper mapper) {
		Object[] objs = executeQuery(sql, psmtSetter, mapper);
		if (objs.length != 1) {
			return null;
		}
		
		return objs[0];
	}
}
