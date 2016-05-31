package issueManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import issueManager.dao.ProjectDao;
import issueManager.dao.UserDao;
import issueManager.model.Project;
import issueManager.model.User;

@Service
public class IssueService {
	@Autowired
	UserDao userDao;
	@Autowired
	ProjectDao projectDao;
	
	@Transactional
	public void insertProject(User loginUser, Project project) {
		Long projectId = projectDao.insert(project.getName());
		projectDao.insertWithEmail(projectId,loginUser.getEmail());
	}

	public Project getProjectInfo(Long projectId) {
		Project saved = projectDao.findById(projectId);
		//project_user 테이블에서 해당 프로젝트참여자 Map가져오기
		//saved에 해당 정보 더해주기 
		return saved;
	}
	
	
	
	
}
