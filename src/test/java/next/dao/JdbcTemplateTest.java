package next.dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.User;

public class JdbcTemplateTest {

	@Before
	public void setUp() throws Exception {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecuteUpdateStringPreparedStatementSetter() {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		
		JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {
		};
		
		insertJdbcTemplate.executeUpdate(sql, expected.getUserId());
		
		
	}

	@Test
	public void testExecuteUpdateStringObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryForObject() {
		fail("Not yet implemented");
	}

}
