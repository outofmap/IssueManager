package issueManager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import issueManager.model.Project;

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
		List<Project> result;
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

}
