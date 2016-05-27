package issueManager.controller.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

	 @Autowired
	 private UserDao userDao;
	// join page get
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String showJoinPage(Model model) {
		// spring form tag에 model.addAttribute 활용해 model-formdata binding 해야함
		model.addAttribute("user", new User());
		return "/user/joinForm";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult,Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			log.debug("user info is NOT valid");
			return "/user/joinForm";
		}
		String hashed = user.encryptPassword(user.getPassword());
		user.setPassword(hashed);
		log.debug("after hased user :" + user.toString());
		try{
			userDao.insert(user);
		} catch(DuplicateKeyException du){
			log.debug(du.getMessage());
			model.addAttribute("sameKey", true);
			return "/user/joinForm";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showloginPage(Model model) {
		// model.addAttribute 해야함
		model.addAttribute("user", new User());
		return "/user/loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult bindingResult, Model model, HttpSession session) throws Exception {
		if (user == null) {
			model.addAttribute("loginFailed", true);
			return "/user/loginForm";
		}
		if (bindingResult.hasErrors()) {
			log.debug("user info is NOT valid");
			return "/user/loginForm";
		}
		User savedUser = userDao.findByEmail(user.getEmail());
		if(user.matchPassword(user.getPassword(),savedUser.getPassword())){
//			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
			log.debug("matched pw!!!!");
		} else {
			log.debug("password no match");
			model.addAttribute("loginFailed", true);
			return "/user/loginForm";
		}
		return "redirect:/";
	}
}
