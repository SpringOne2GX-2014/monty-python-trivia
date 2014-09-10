package org.demo.integration.mockmvc;

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static java.util.Arrays.asList;

import org.demo.domain.Category;
import org.demo.repository.CategoryRepository;
import org.demo.repository.QuestionRepository;
import org.demo.web.CategoryController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class MockMvcStandaloneTest {
	@Mock
	CategoryRepository categoryRepository;
	@Mock
	QuestionRepository questionRepository;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.standaloneSetup(new CategoryController(categoryRepository,questionRepository))
				.alwaysDo(print()) // Optional
				.build();

		when(categoryRepository.findAll()).thenReturn(asList(new Category(1,"category 1"), new Category(2, "category 2")));
	}

	@Test
	public void categoriesBasic() throws Exception {
		mockMvc.perform(get("/categories"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void categoriesJsonPath() throws Exception {
		mockMvc.perform(get("/categories"))
			.andExpect(jsonPath("$.[*].name[*]").value(contains("category 1","category 2")));
	}
}
