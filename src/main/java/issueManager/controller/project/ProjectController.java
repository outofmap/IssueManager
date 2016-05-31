package issueManager.controller.project;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public String make(@LoginUser User loginUser,Project project) {
		logger.debug("inin");
		return null;
	}
	
	//특정 프로젝트 페이지 
	@RequestMapping(value = "/{projectId}", method= RequestMethod.GET)
	public String showListPage(Model model) {
		return "/project/issueList";
	}
	
	
}
