package issueManager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import issueManager.model.Issue;


@Repository
public class IssueDao {
	private static final Logger logger = LoggerFactory.getLogger(IssueDao.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void deleteAllByPId(Long projectId) {
		String sql = "DELETE FROM issue where projectId = ?";
		jdbcTemplate.update(sql, projectId);
		logger.debug("delete All issue By ProjectId");
	}

	public List<Issue> getIssuelist(Long projectId) {
		String sql = "select issueId,writer,title,user_email from issue where projectId =?";
		RowMapper<Issue> rm = new RowMapper<Issue>() {

			@Override
			public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Issue(rs.getLong("issueId"),rs.getString("writer"),
						rs.getString("title"),rs.getString("user_email"));
			}
		};
		return jdbcTemplate.query(sql, rm, projectId);
	}

//	public List<Issue> findAllByPId(Long projectId) {
//		String sql = "select * from issue where projectId = ?";
//
//		return jdbcTemplate.query(sql, rm,projectId);
//	}

}
