package org.demo.integration.mockmvc;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.demo.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@WebAppConfiguration
public class MockMvcTest {
	
	@Autowired
	WebApplicationContext context;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.alwaysDo(print()) // Optional
				.build();
	}

	@Test
	public void categoriesBasic() throws Exception {
		mockMvc.perform(get("/categories"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void categoriesJsonPath() throws Exception {
		ResultMatcher containsMovies = jsonPath("$.[*].name[*]")
			.value(contains(
				"Holy Grail",
				"Cheese Shop",
				"Life of Brian",
				"International Philosophy Match"));
		
		mockMvc.perform(get("/categories"))
			.andExpect(containsMovies);
	}

	@Test
	public void formPost() throws Exception {
		mockMvc.perform(post("/").param("question", "1"))
			.andExpect(model().attributeExists("question"));
	}
}
