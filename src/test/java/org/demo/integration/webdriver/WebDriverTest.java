/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.demo.integration.webdriver;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.demo.Config;
import org.demo.integration.pages.AnswerPage;
import org.demo.integration.pages.QuestionPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@WebAppConfiguration
public class WebDriverTest {
	@Autowired
	WebApplicationContext context;

	WebDriver driver;

	@Before
	public void setup() {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.alwaysDo(print()) // Optional
				.build();

		Capabilities capabilities = DesiredCapabilities.firefox(); 
		driver = new MockMvcHtmlUnitDriver(mockMvc, capabilities) {
			@Override
			protected WebClient configureWebClient(WebClient client) {
				client = super.configureWebClient(client);
				client.setAjaxController(new NicelyResynchronizingAjaxController());
				return client;
			}
		};
	}

	@Test
	public void whatDoKnightsOfNiSay() throws Exception {
		QuestionPage question = QuestionPage.to(driver);
		
		question.selectMovieOption("Holy Grail");
		
		question.selectQuestionOption("What do the Knights of Ni say?");
		
		question.submit();
		
		AnswerPage answer = AnswerPage.at(driver);
		
		assertTrue(answer.hasQuestion("What do the Knights of Ni say?"));
		assertTrue(answer.hasAnswer("Ni!"));
	}
}