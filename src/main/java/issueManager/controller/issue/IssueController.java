package issueManager.controller.issue;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import issueManager.dao.IssueDao;
import issueManager.dao.ProjectDao;
import issueManager.dao.ReplyDao;
import issueManager.model.Issue;
import issueManager.model.Reply;
import issueManager.model.User;
import issueManager.service.IssueService;
import web.argumentresolver.LoginUser;

@Controller
@RequestMapping("/projects/{projectId}/issues")
public class IssueController {
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	@Resource
	IssueService service;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	IssueDao issueDao;
	@Autowired
	ReplyDao replyDao;

	// post만들기
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String make(@LoginUser User loginUser, @PathVariable Long projectId, @Valid Issue issue, Model model) {
		if (!loginUser.isGuestUser()) {
			logger.debug("proejctId:" + projectId.toString());
			logger.debug("issue title:" + issue.getTitle());
			Issue saved = service.insertIssue(loginUser, projectId, issue);
			// member인 경우 이슈 생성 가능
			if (saved != null) {
				model.addAttribute("issue", saved);
				// 개별 이슈 페이지로 보내기 개별 이슈페이지를 만들자!
				return "redirect:/projects/{projectId}/issues/" + saved.getIssueId();
			} else {
				logger.debug("member가 아님.");
				model.addAttribute("projectId", projectId);
				return "/issue/unable";
			}
		} else {
			return "redirect:/users/login";
		}
	}

	// 이슈개별 보기 만들기
	@RequestMapping(value="/{issueId}", method = RequestMethod.GET)
	public String show(@LoginUser User loginUser, @PathVariable Long issueId, Model model,
			@PathVariable Long projectId){
		Issue saved = issueDao.select(issueId);
		List<Reply> replys = replyDao.findAll(issueId);
		model.addAttribute("issue", saved);
		model.addAttribute("replys", replys);
		model.addAttribute("projectId",projectId);
		return "/issue/issue";
	}

}
