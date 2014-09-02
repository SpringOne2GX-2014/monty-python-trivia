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
package org.demo.integration.htmlunit;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.demo.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@WebAppConfiguration
public class HtmlUnitTest {
	@Autowired
	WebApplicationContext context;

	WebClient webClient;
	
	@Before
	public void setup() {
		MockMvc mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.alwaysDo(print()) // Optional
				.build();
		
		webClient = new WebClient(BrowserVersion.FIREFOX_24);
		webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	}

	@Test
	public void whatDoKnightsOfNiSay() throws Exception {
		HtmlPage index = webClient.getPage("http://localhost/mpt/");
		assertEquals("Monty Python Trivia", index.getTitleText());
		
		HtmlForm form = (HtmlForm) index.getByXPath("//form").get(0);
		
		HtmlSelect movie = form.getSelectByName("movie");
		HtmlOption holyGrail = movie.getOptionByText("Holy Grail");
		movie.setSelectedAttribute(holyGrail, true);
		
		HtmlSelect question = form.getSelectByName("question");
		HtmlOption knightsSay = question.getOptionByText("What do the Knights of Ni say?");
		question.setSelectedAttribute(knightsSay, true);
		
		HtmlSubmitInput submit = (HtmlSubmitInput) index.getElementById("submit");
		HtmlPage answer = submit.click();
		
		DomElement questionElmt = answer.getElementById("questionDisplay");
		DomElement answerElmt = answer.getElementById("answerDisplay");
		
		assertEquals("What do the Knights of Ni say?", questionElmt.getTextContent());
		assertEquals("Ni!", answerElmt.getTextContent());
	}
}