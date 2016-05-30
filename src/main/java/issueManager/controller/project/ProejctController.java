package issueManager.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/proejcts")
public class ProejctController {
	//특정 프로젝트 페이지 
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public String showListPage(Model model) {
		return "/project/issueList";
	}
}
