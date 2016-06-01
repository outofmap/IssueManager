package issueManager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import issueManager.dao.IssueDao;
import issueManager.dao.ProjectDao;
import issueManager.dao.UserDao;
import issueManager.model.Issue;
import issueManager.model.Project;
import issueManager.model.User;

@Service
public class IssueService {
	private static final Logger logger = LoggerFactory.getLogger(IssueService.class);
	@Autowired
	UserDao userDao;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	IssueDao issueDao;

	@Transactional
	public void insertProject(User loginUser, Project project) {
		Long projectId = projectDao.insert(project.getName());
		projectDao.insertWithEmail(projectId, loginUser.getEmail());
	}

	public Project getProjectInfo(Long projectId) {
		Project saved = projectDao.findById(projectId);
		List<User> members = projectDao.getMembers(projectId);
		logger.debug("members"+members.toString());
		if (!members.isEmpty()) {
			saved.setUserList(members);
			logger.debug("project members" + saved.getUserList());
		} 
		return saved;
	}

	public Project getProjectByUser(Long projectId, String email) {
		List<Project> projectList = projectDao.findbyEmail(email);
		logger.debug("proejctlist with email : " + projectList.toString());
		return matchByprojectId(projectId, projectList);

	}

	private Project matchByprojectId(Long projectId, List<Project> projectlist) {
		for (Project project : projectlist) {
			if (project.getProjectId().equals(projectId)) {
				return project;
			}
		}
		// proejcId와 match되는 project가 없음. user는 projectId에 참여하지 않음. .
		return null;
	}

	public void deleteProject(Long projectId) {
		// TODO Auto-generated method stub
		// ISSUE에 있는 reply 지우기
		// issueDao.findAllByPId(projectId);
		// delete issuelist
		// issue table에서 projectId 지우기
		issueDao.deleteAllByPId(projectId);
		// project_user table 에서 지우기
		projectDao.deleteWithMembers(projectId);
		// project table에서 지우기
		projectDao.delete(projectId);

	}

	public List<Issue> getIssuelistByPId(Long projectId) {
		return issueDao.getIssuelist(projectId);
		
	}

}
