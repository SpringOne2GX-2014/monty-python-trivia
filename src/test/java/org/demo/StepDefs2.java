package org.demo;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

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


@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class StepDefs2 {
	@Autowired private WebApplicationContext context;
	MockMvcHtmlUnitDriver driver;
	IndexPage indexPage;

	@Before
	public void setup() throws IOException {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		//	The tests always failed when executing JavaScript such as document.addEventListener()
		//	(as found in JQuery).  Adding this chrome capability patched up the problem.
		Capabilities capabilities = DesiredCapabilities.chrome();
		driver = new MockMvcHtmlUnitDriver(mockMvc, capabilities);
	}

	@After
	public void destroy() {
		if(driver != null) {
			driver.close();
		}
	}

	//	TODO:  WHERE I LEFT OFF
	//	I can get to the landing page as long as I use the chrome capabilities above.
	//	The movie selection box is emply, though I have verified that the ajax call reaches the server.  I think the DOM is not being updated, and I don't think it is a matter of wait time because of when the breakpoints got hit.
	
	@When("^I go to the landing page$")
	public void i_go_to_the_landing_page() throws Throwable {
		
		//	The driver expects "http" to establish which protocol to use.  
		//	Don't know why it needs localhost.
		//	Also don't know why it needs the servlet mapping or how it determined 'mpt' since this is only known to maven or eclipse; it is not in any code.
//		driver.get("http://localhost/mpt/");
		indexPage = IndexPage.to(driver);
	}


	@Then("^I expect to see a list of Monty Python movies$")
	public void i_expect_to_see_movie_list() throws Throwable {
//		WebElement webElement = driver.findElementByName("movie");
//		assertTrue("Can't find the selection element with movies and skits.", webElement != null);
//		assertTrue("Movie list should be visible.", webElement.isDisplayed() );
//		assertTrue("Movie selections should be enabled.", webElement.isEnabled());
//
//
//		List<WebElement> options = webElement.findElements(By.tagName("option"));
//		assertTrue("Should have at least 3 options in the list", options.size() > 3);  //Presently failing now that I'm populating this with AJAX, static was fine.
//		for (WebElement option : options) {
//			System.out.println(option.getText());
//		}

		assertTrue("Should have at least 3 options in the list", indexPage.getNumberOfMovieOptions() > 3);  //Presently failing now that I'm populating this with AJAX, static was fine.
		
		
	}

	@Then("^one of the options should be 'Holy Grail'$")
	public void i_expect_holy_grail() throws Throwable {
		WebElement webElement = driver.findElementByName("movie");
		List<WebElement> options = webElement.findElements(By.tagName("option"));
		
		String text;
		boolean foundTheHolyGrail = false;
		for (WebElement option : options) {
			text = option.getText();
			if ("Holy Grail".equals(text)) {
				foundTheHolyGrail = true;
				break;
			}
		}
		assertTrue("Holy Grail doesn't seem to be one of the options.", foundTheHolyGrail);

	}


}
