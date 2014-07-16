package org.demo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

public class WebClientTests {

	//	This is a use of the raw HTMLUnit WebClient.  It requires a server running and a
	//	URL that you can actually hit.  Good for debugging problems.
	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	    WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getPage("http://localhost:8080/mpt/dummy");
	}

	@Test
	public void testWebDriver() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://localhost:8080/mpt/dummy");
		System.out.println( "Page Source: " + driver.getPageSource() );
	}
}
