package issueManager.controller.project;

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
	
	//project 생성 
	@RequestMapping(value="", method=RequestMethod.POST)
	public String make(@LoginUser User loginUser,Project project,Model model) {
		logger.debug(loginUser.getClass().getName());
		if(!loginUser.isGuestUser()){
			service.insertProject(loginUser, project);
			return "redirect:/";
		} else {
			return "redirect:/users/login";
		}
	}
	
	//특정 프로젝트 페이지 
	@RequestMapping(value = "/{projectId}", method= RequestMethod.GET)
	public String showListPage(@PathVariable Long projectId, Model model) {
		Project savedProject = service.getProjectInfo(projectId);
		logger.debug("found project info{}"+savedProject.toString());
		model.addAttribute("project", savedProject);
		return "/project/issueList";
	}
	
	@RequestMapping(value = "/{projectId}/join", method= RequestMethod.GET)
	public String joinThisProject(@PathVariable Long projectId, Model model,@LoginUser User loginUser) {
		if(loginUser.isGuestUser()){
//			Project savedProject = service.getProjectInfo(projectId);
//			saveProject에 있는 userlist중 loginUser찾기 by email savedProejct.hasUser()
//		성공적으로 추가됐으면 redirect
			//아닌 경우 멤버가 아닌 것임. 
		return "redirect:/projects/"+projectId;
		}
		return null;
	}
	@RequestMapping(value="/{projectId}/edit", method=RequestMethod.GET)
	public String showEditPage(@PathVariable Long projectId, @LoginUser User loginUser){
		//loginUser가 해당 proejctId의 join해 있는 지 확인한다. 
		return "/project/editForm";
	}
}
