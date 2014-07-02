package org.demo.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class FormController {

	@RequestMapping(method=RequestMethod.GET)
	public String showForm() {
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String showResults (
			HttpSession session, 
			@RequestParam String movie, 
			@RequestParam String question) {

		//	Bad code: Session use...
		session.setAttribute("movie", movie);
		session.setAttribute("question", question);
		return "redirect:results";
	}
}
