package issueManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import issueManager.dao.ProjectDao;
import issueManager.model.Project;

@Controller
public class HomeController {
	@Autowired
	ProjectDao projectDao;
	
	@RequestMapping(value = "/", method= RequestMethod.GET)
	public String showMainPage(Model model) {
		List<Project> projects = projectDao.findAll();
		model.addAttribute("projects",projects);
		return "index";
	}
}
