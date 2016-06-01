package issueManager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import issueManager.dao.ProjectDao;
import issueManager.dao.UserDao;
import issueManager.model.Project;
import issueManager.model.User;

@Service
public class IssueService {
	private static final Logger logger = LoggerFactory.getLogger(IssueService.class);
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

	public Project getProjectByUser(Long projectId, String email) {
		List<Project> projectList = projectDao.findbyEmail(email);
		logger.debug("proejctlist with email : "+projectList.toString());
		return matchByprojectId(projectId, projectList);
		
	}

	private Project matchByprojectId(Long projectId,List<Project> projectlist) {
		for (Project project : projectlist) {
			if (project.getProjectId().equals(projectId)) {
				return project;
			} 
		}
		//proejcId와 match되는 project가 없음. user는 projectId에 참여하지 않음. . 
		return null;
	}
	
	
	
	
}
