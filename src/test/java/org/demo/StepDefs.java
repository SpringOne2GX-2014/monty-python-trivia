package org.demo;

import static org.junit.Assert.fail;

import java.io.IOException;

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
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class StepDefs {

	@Autowired WebApplicationContext context;
	MockMvcHtmlUnitDriver driver;

	@Before
	public void setup() throws IOException {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		Capabilities capabilities = DesiredCapabilities.chrome();
		driver = new MockMvcHtmlUnitDriver(mockMvc, capabilities);
	}

	@After
	public void destroy() {
		if(driver != null) {
			driver.close();
		}
	}


	@Given("^I am on the first page$")
	public void on_first_page() throws Throwable { 
		driver.get("http://localhost/mpt/");		
	}

	@When("^I select 'Holy Grail'$")
	public void i_select_category() throws Throwable {
		WebElement webElement = driver.findElementByName("movie");

	}

	@And("^I select 'What do the Knights of Ni say'?$")
	public void i_select_question() throws Throwable { }
	
	@And("^I press submit$")
	public void submit() throws Throwable { }

	@Then("^I should see the answer page$")
	public void on_answer_page() throws Throwable { }

	@And("^I should see the question displayed$")
	public void question_displayed() throws Throwable { }

	@And("^I should see the answer 'Ni!'$")
	public void answer_displayed() throws Throwable { }
}
