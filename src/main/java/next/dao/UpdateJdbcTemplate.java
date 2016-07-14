package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class UpdateJdbcTemplate {
	public void update (User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = createQueryForUpdate();
			pstmt = con.prepareStatement(sql);
			setValuesForUpdate(user, pstmt);
	
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
	
	public abstract void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException;
	public abstract String createQueryForUpdate();
}
