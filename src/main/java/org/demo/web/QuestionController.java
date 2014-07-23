package org.demo.web;

import org.demo.domain.Question;
import org.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {

	@Autowired QuestionRepository repository;
	
	@RequestMapping("/questions/{id}")
	public @ResponseBody Question getQuestion(@PathVariable long id) {
		return repository.findOne(id);
	}
}
