package org.demo.web;

import org.demo.domain.Category;
import org.demo.domain.Question;
import org.demo.repository.CategoryRepository;
import org.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {

	@Autowired CategoryRepository categoryRepository;
	@Autowired QuestionRepository questionRepository;
	
	@RequestMapping("/categories")
	public @ResponseBody Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@RequestMapping("/categories/{id}/questions")
	public @ResponseBody Iterable<Question> getQuestionsForCategory(@PathVariable long id) {
		return questionRepository.findByCategory(id);
	}
}
