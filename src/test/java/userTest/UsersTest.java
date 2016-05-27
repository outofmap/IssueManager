package userTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import issueManager.dao.UserDao;
import issueManager.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UsersTest {
	private static final Logger logger = LoggerFactory.getLogger(UsersTest.class);
	@Mock
	private UserDao userDao;
	private User testUser;
	
	@Before
	public void setup() {
		logger.debug("in");
		testUser = new User("test@test.com","bono","pass");
	}
	
	@Test
	public void hashPassword() {
		String testpw = "testpw";
		String after = testUser.encryptPassword(testpw);
		Assert.assertNotEquals(testpw, after);
	}
	
	@Test
	public void matchPassword() {
		//실제로는 사용자가 입력할 pw 
		String testpw = "123456tt";
		//로그인 사용자의 저장된 hashed pw 
		String hashed = testUser.encryptPassword(testpw);
		boolean result = testUser.matchPassword(testpw, hashed);
		Assert.assertTrue(result);
	}
}
