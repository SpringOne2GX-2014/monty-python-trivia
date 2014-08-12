package org.demo.web;

import org.demo.domain.Question;
import org.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class FormController {

	@Autowired QuestionRepository repository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm() {
		return "question";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String displayResults(@RequestParam("question") Long questionId, Model model) {

		Question question = repository.findOne(questionId);
		model.addAttribute("question", question);
		
		return "answer";
	}	
}
