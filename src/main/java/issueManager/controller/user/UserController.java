package issueManager.controller.user;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import issueManager.dao.UserDao;
import issueManager.model.User;


@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
//	@Autowired
//	private UserDao userDao;
	//join page get
	@RequestMapping(value="/join",method= RequestMethod.GET)
	public String showJoinPage(Model model) {
		//spring form tag에 model.addAttribute 활용해 model-formdata binding 해야함 
		model.addAttribute("user", new User());
		return "/user/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult) throws Exception {
    	if(bindingResult.hasErrors()){
    		log.debug("user info is NOT valid");
    		return "/user/joinForm";
    	}
        String hashed = user.encryptPassword(user.getPassword());
        user.setPassword(hashed);
    	log.debug("after hased user :"+user.toString());
//        userDao.insert(user);
		return "redirect:/";
	}
	
	@RequestMapping(value="/loginPage",method= RequestMethod.GET)
	public String showloginPage() {
		//model.addAttribute 해야함 
		return "/user/loginForm";
	}
}
