package org.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;


@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class StepDefs {
	@Autowired private WebApplicationContext context;
	MockMvcHtmlUnitDriver driver;

	@Before
	public void setup() throws IOException {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		driver = new MockMvcHtmlUnitDriver(mockMvc, true);
	}

	@After
	public void destroy() {
		if(driver != null) {
			driver.close();
		}
	}

	//	TODO:  WHERE I LEFT OFF
	//	This code came from excellent drums.  It runs but fails on the landing page
	//	because the page itself is trying to run JQuery, and does not know what a 'document' is.
	//	To simplify the test I am going to the 'dummy' page with 2 lines of JavaScript, and it still
	//	does not know what the 'document' object is.
	
	@When("^I go to the landing page$")
	public void i_go_to_the_landing_page() throws Throwable {
		
		//	The driver expects "http" to establish which protocol to use.  
		//	Don't know why it needs localhost.
		//	Also don't know why it needs the servlet mapping or how it determined 'mpt' since this is only known to maven or eclipse; it is not in any code.
		driver.get("http://localhost/mpt/dummy");
		System.out.println("Page source is:" + driver.getPageSource());
	}


	@Then("^I expect to see a list of Monty Python movies$")
	public void i_expect_to_see_movie_list() throws Throwable {
		assertTrue(driver.findElementByName("movie") != null);
	}
}
