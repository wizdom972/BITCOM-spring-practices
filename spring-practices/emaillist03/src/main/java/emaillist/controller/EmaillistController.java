package emaillist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import emaillist.repository.EmaillistRepository;
import emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	private EmaillistRepository emaillistRepository;

	public EmaillistController(EmaillistRepository emaillistRepository) {
		this.emaillistRepository = emaillistRepository;
	}

	@RequestMapping("/")
	public String index(Model model) {
		List<EmaillistVo> list = emaillistRepository.findAll();
		model.addAttribute("list", list);

		return "index";
	}

	@RequestMapping("/form")
	public String form() {
		return "form";
	}

	@RequestMapping("/add")
	public String add(EmaillistVo vo) {
		emaillistRepository.insert(vo);
		return "redirect:/";
	}
}
