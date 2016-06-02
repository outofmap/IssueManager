package issueManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import issueManager.model.Issue;
import issueManager.model.Project;
import issueManager.model.User;


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

	public Long insert(Issue issue, Long projectId, User loginUser) {
		// TODO Auto-generated method stub
		String sql = "insert into issue (writer,title,contents,projectId,user_email) values (?,?,?,?,?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, loginUser.getName());
				pstmt.setString(2, issue.getTitle());
				pstmt.setString(3, issue.getContents());
				pstmt.setLong(4, projectId);
				pstmt.setString(5, loginUser.getEmail());
				return pstmt;
			}
		};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc,keyHolder);
		return keyHolder.getKey().longValue();
		
	}

	public Issue select(Long issueId) {
		String sql = "select * from issue where issueId = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Issue.class),issueId);
		
	}

}
