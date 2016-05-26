package issueManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method= RequestMethod.GET)
	public String showMainPage() {
		//model.addAttribute 해야함 
		return "index";
	}
}
