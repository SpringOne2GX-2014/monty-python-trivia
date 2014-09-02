/*
 * Copyright 2002-2013 the original author or authors.
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
package org.demo.integration.geb

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.demo.integration.geb.pages.*

import geb.spock.GebReportingSpec

import org.demo.Config
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

import spock.lang.Stepwise

@ContextConfiguration(classes = Config.class)
@WebAppConfiguration
@Stepwise
class GebTest extends GebReportingSpec {
	@Autowired
	WebApplicationContext context
	
	def setup() {
	    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysDo(print()).build()
	    Capabilities capabilities = DesiredCapabilities.firefox(); 
		browser.driver = new MockMvcHtmlUnitDriver(mockMvc, capabilities) {
			@Override
			protected WebClient configureWebClient(WebClient client) {
				client = super.configureWebClient(client)
				client.ajaxController = new NicelyResynchronizingAjaxController()
				return client;
			}
		}
	}

	def 'I select Holy Grail'() {
		setup:
		def toAsk = 'What do the Knights of Ni say?'
		when: 'I select Holy Grail'
		to QuestionPage
		movie = 'Holy Grail'
		askQuestion toAsk
		then: 'The answer is displayed'
		at AnswerPage
		question == toAsk
		answer == 'Ni!'
	}
}
