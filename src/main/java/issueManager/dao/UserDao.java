package issueManager.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import issueManager.model.User;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	// 회원가입
	public void insert(User user) throws DuplicateKeyException {
		String sql = "INSERT INTO user VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, user.getEmail(), user.getName(), user.getPassword());
		logger.debug("inserted user: "+user.toString());
	}

	public User findByEmail(String email) {
		String sql = "SELECT * FROM USER WHERE email=?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
	}

}
