package org.demo.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.demo.Config;
import org.demo.domain.Category;
import org.demo.domain.Question;
import org.demo.web.CategoryController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class BasicIntegrationTest {

	@Autowired CategoryController controller;
	
	@Test
	public void testCategories() {
		Iterable<Category> iterable = controller.getCategories();
		assertNotNull(iterable);
		assertEquals( "Holy Grail", iterable.iterator().next().getName() ) ;
	}

	
	@Test
	public void testQuestions() {
		Iterable<Question> iterable = controller.getQuestionsForCategory(1);
		assertNotNull(iterable);
		assertEquals( "What do the Knights of Ni say?", iterable.iterator().next().getQuestion() ) ;
	}

}
