package issueManager.controller.project;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import issueManager.dao.ProjectDao;
import issueManager.model.Issue;
import issueManager.model.Project;
import issueManager.model.User;
import issueManager.service.IssueService;
import web.argumentresolver.LoginUser;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Resource
	IssueService service;
	@Autowired
	ProjectDao projectDao;

	// project 생성
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String make(@LoginUser User loginUser, Project project, Model model) {
		logger.debug(loginUser.getClass().getName());
		if (!loginUser.isGuestUser()) {
			service.insertProject(loginUser, project);
			logger.debug("in");
			return "redirect:/";
		} else {
			return "redirect:/users/login";
		}
	}

	// 특정 프로젝트 페이지 member / issue 보여주기 
	@RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
	public String show(@PathVariable Long projectId, Model model) {
		Project savedProject = service.getProjectInfo(projectId);
		List<Issue> issuelist = service.getIssuelistByPId(projectId);
		logger.debug("found project info{}" + savedProject.toString());
		model.addAttribute("members", savedProject.getUserList());
		model.addAttribute("project", savedProject);
		model.addAttribute("issues",issuelist);
		return "/project/issueList";
	}

	//method와 실제 처리내용이 안맞음 get메소드인데 user_project table에 member로 등록함. 
	@RequestMapping(value = "/{projectId}/join", method = RequestMethod. GET)
	public String joinThisProject(@PathVariable Long projectId, Model model, @LoginUser User loginUser) {
		if (!loginUser.isGuestUser()) {
			Project savedProject = service.getProjectInfo(projectId);
			if(savedProject.hasUser(loginUser.getEmail())){
				//이미 등록한 유저임. 
				return "redirect:/projects/" + projectId;
			}
			projectDao.insertWithEmail(projectId, loginUser.getEmail());
			return "redirect:/projects/" + projectId;
		}
		return "redirect:/users/login";
	}

	@RequestMapping(value = "/{projectId}/edit", method = RequestMethod.GET)
	public String showEditPage(@PathVariable Long projectId, @LoginUser User loginUser, Model model) {
		if (!loginUser.isGuestUser()) {
			// loginUser가 해당 proejctId의 member인지 확인한다. 
			Project usersProject = service.getProjectByUser(projectId, loginUser.getEmail());
			if (usersProject != null) {
				model.addAttribute("project", usersProject);
				return "/project/editForm";
			} else {
				logger.debug("user는 다른 프로젝트는 참여했지만, 이 프로젝트는 user가 참여하지 않은 프로젝트임.");
				return "/project/unableToEdit";
			}
		} else {
			// guest는 수정 권한 없음.
			return "redirect:/users/login";
		}
	}
	//프로젝트 이름 수정하기 
	@RequestMapping(value = "/{projectId}", method = RequestMethod.PUT)
	public String edit(@PathVariable Long projectId, @LoginUser User loginUser, Project project) {
		if(!loginUser.isGuestUser()){
			projectDao.update(projectId, project.getName());
			return "redirect:/";
		}else {
			return "redirect:/users/login";
		}
	}
	//프로젝트 삭제하기  
	@RequestMapping(value = "/{projectId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long projectId, @LoginUser User loginUser, Project project) {
		if(!loginUser.isGuestUser()){
			service.deleteProject(projectId);
			return "redirect:/";
		}else {
			return "redirect:/users/login";
		}
	}
	
	

}
