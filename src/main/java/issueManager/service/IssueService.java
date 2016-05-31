package issueManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import issueManager.dao.ProjectDao;
import issueManager.dao.UserDao;
import issueManager.model.Project;
import issueManager.model.User;

@Service
public class IssueService {
	@Autowired
	UserDao userDao;
	@Autowired
	ProjectDao proejctDao;
	
	public void makeProject(User loginUser, Project project) {
		// TODO Auto-generated method stub
		
	}
	
	
}
