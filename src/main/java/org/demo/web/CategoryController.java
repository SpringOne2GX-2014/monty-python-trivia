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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

	@Autowired CategoryRepository categoryRepository;
	@Autowired QuestionRepository questionRepository;
	
	@RequestMapping("/categories")
	public Iterable<Category> getCategories() {
		
		//	This was failing when running in HtmlUnit due to a transaction serialization error.  So instead, just populate the list manually.
		//	FIXED - The problem was populating the database via schema.sql and data.sql, which gets deleted by hibernate during startup (because
		//	spring boot assumes an in memory database should always start empty.  So instead moved the initialization to hibernate specific import.sql:
		Iterable<Category> results = categoryRepository.findAll();
//		List <Category> results = new ArrayList<Category>();
//		results.add(new Category(1, "Holy Grail"));
//		results.add(new Category(2, "Cheese Shop"));
//		results.add(new Category(3, "Life of Brian"));
//		results.add(new Category(4, "International Philosophy Match"));
		
		return results; 
	}

	@RequestMapping("/categories/{id}/questions")
	public Iterable<Question> getQuestionsForCategory(@PathVariable long id) {
		Iterable<Question> questions = questionRepository.findByCategory(id);
		return questions;
	}
}
