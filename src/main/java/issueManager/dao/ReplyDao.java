package issueManager.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import issueManager.model.Reply;

@Repository
public class ReplyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public List<Reply> findAll(Long issueId) {
		List<Reply> result;
		String sql = "select * from reply where issueId = ?";
		result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Reply>(Reply.class),issueId);
		return result;
	}
}
