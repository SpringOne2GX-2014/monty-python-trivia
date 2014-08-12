package org.demo.integration.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.find.PageTitleFinder;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AnswerPage {
	
	public static final String pageTitle = "Monty Python Trivia - Answer";
	
	private WebElement questionDisplay;
	private WebElement answerDisplay;
	
	/**
	 * Determine if the page the browser is on right now is the page represented by this class.
	 */
	public static boolean isCurrentPage(WebDriver webDriver) {
		return webDriver.getTitle().equals(pageTitle);
	}
	
	/**
     * Assuming we are on the AnswerPage, have WebDriver return an initialized AnswerPage object.  
     * If we are not presently on the AnswerPage, this will throw an Exception.
     */
    public static AnswerPage at(WebDriver driver) {
		if (!isCurrentPage(driver)) {
			throw new RuntimeException("Web Driver is not presently positioned on the Answer page.");
		}
        return PageFactory.initElements(driver, AnswerPage.class);
    }	
	
    public boolean hasQuestion(String question) {
    	return questionDisplay.getText().equals(question);
    }

    public boolean hasAnswer(String answer) {
    	return answerDisplay.getText().equals(answer);
    }
}
