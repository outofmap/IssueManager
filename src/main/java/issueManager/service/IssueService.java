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

	@Transactional
	public Project getProjectInfo(Long projectId) {
		Project saved = projectDao.findById(projectId);
		List<User> members = projectDao.getMembers(projectId);
		logger.debug("members" + members.toString());
		if (!members.isEmpty()) {
			saved.setUserList(members);
			logger.debug("project members" + saved.getUserList());
		}
		return saved;
	}
	
	public Project getProjectByUser(Long projectId, String email) throws NotMemberException {
		Project project = getProjectInfo(projectId);
		if (project.hasUser(email)) {
			return project;
		} else {
			throw new NotMemberException();
		}

	}

	@Transactional
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

	public Issue insertIssue(User loginUser, Long projectId, Issue issue) {
		if (isMemeber(projectId, loginUser)) {
			//isert query 작성하기 
			Long issueId = issueDao.insert(issue, projectId, loginUser);
			return issueDao.select(issueId);
		} else {
			return null;
		}
	}

	public boolean isMemeber(Long projectId, User loginUser) {
		List<User> members = projectDao.getMembers(projectId);
		logger.debug("member in project", members.toString());
		for (User member : members) {
			if (member.isSameUser(loginUser)) {
				return true;
			}
		}
		return false;
	}

}
