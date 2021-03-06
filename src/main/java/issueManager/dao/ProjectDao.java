package issueManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import issueManager.model.Project;
import issueManager.model.User;

@Repository
public class ProjectDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public List<Project> findAll() {
		List<Project> result;
		String sql = "select * from project";
		result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Project>(Project.class));
		return result;
	}

	public List<Project> findbyEmail(String email) {
		String sql = "select p.projectId, p.name from project as p inner join project_user as pu "
				+ "on p.projectId = pu.projectId where pu.email = ?";

		RowMapper<Project> rm = new RowMapper<Project>() {
			@Override
			public Project mapRow(ResultSet rs, int index) throws SQLException {
				return new Project(rs.getLong("p.projectId"), rs.getString("p.name"));
			}
		};
		return jdbcTemplate.query(sql, rm, email);

	}

	public Long insert(String name) {
		String sql  = "insert into project (name) values (?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1,name);
				return pstmt;
			}
		};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc,keyHolder);
		return keyHolder.getKey().longValue();
	}

	public void insertWithEmail(Long projectId, String email) {
		String sql = "insert into project_user (projectId,email) values (?,?)";
		jdbcTemplate.update(sql, projectId, email);
		
	}

	public Project findById(Long projectId) {
		String sql = "select * from project where projectId = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Project.class),projectId);
	}

	public void update(Long projectId, String name) {
		String sql = "UPDATE project SET name=? WHERE projectId  = ?";
		jdbcTemplate.update(sql, name, projectId);
	}

	public void delete(Long projectId) {
		String sql = "DELETE FROM project where projectId = ?";
		jdbcTemplate.update(sql, projectId);
		logger.debug("delete project By ProjectId");
		
	}

	public void deleteWithMembers(Long projectId) {
		String sql = "DELETE FROM project_user where projectId = ?";
		jdbcTemplate.update(sql, projectId);
		logger.debug("delete All P_U By ProjectId");
		
	}

	public List<User> getMembers(Long projectId) {
		String sql = "select u.email, u.name from user as u inner join project_user as pu "
				+ "on u.email = pu.email where pu.projectId = ?";

		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				return new User(rs.getString("u.email"), rs.getString("u.name"));
			}
		};
		return jdbcTemplate.query(sql, rm, projectId);

		
	}
	
	

}
