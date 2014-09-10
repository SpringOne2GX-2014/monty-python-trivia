package org.demo.integration.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class QuestionPage  {
	
	private WebElement movie;
    private WebElement question;

    @FindBy(css = "input[type=submit]")
    private WebElement submitButton;

	public static String getUrl() {
		return "http://localhost/mpt/";
	}

    
	/**
     * Have WebDriver go to the index page, and return an 
     * object that represents this page in future tests.
     */
    public static QuestionPage to(WebDriver driver) {
		//	The driver expects "http" to establish which protocol to use.  
		//	Don't know why it needs localhost.
		//	Also don't know why it needs the servlet mapping or how it determined 'mpt' since this is only known to maven or eclipse; it is not in any code.
        driver.get(getUrl());
        return PageFactory.initElements(driver, QuestionPage.class);
    }
    
    
    /**
     * Obtain and return the # of options in the 'movie' selection element.
     */
    public int getNumberOfMovieOptions() {
		testElementBasics(movie);
	    	Select select = new Select(movie);
	    	return select.getOptions().size();
    }

    
    /**
     * See if the given option is present in the movie selection list, like "Holy Grail".
     */
    public boolean isMovieOptionPresent(String movieOption) {
		testElementBasics(movie);
    		Select select = new Select(movie);
		List<WebElement> options = select.getOptions();
		
		String text;
		boolean found = false;
		for (WebElement option : options) {
			text = option.getText();
			if (movieOption.equals(text)) {
				found = true;
				break;
			}
		}
		return found;
    }

    /**
     *	Select the given movie from the list of movies, like "Holy Grail". 
     */
    public void selectMovieOption(String movieOption) {
		selectOption(movie, movieOption);
    }
    
    
    /**
     *	Select the given question from the list of questions, like "What do the Knights of Ni say?". 
     */
    public void selectQuestionOption(String questionOption) {
		selectOption(question, questionOption);
    }
    
    
    public void submit() {
    	submitButton.click();
    }
    
    private void selectOption(WebElement webElement, String optionToSelect) {
		testElementBasics(webElement);
    	Select select = new Select(webElement);
    	select.selectByVisibleText(optionToSelect); // Will get an exception if the option isn't found.
    }
    
    
    /**
     * There are a few general items that you need to assert about almost every WebElement
     * before you do any meaningful tests, such as if it is present, visible, and enabled.
     */
    private void testElementBasics(WebElement webElement) {
		assertTrue("Can't find the selection element with movies and skits.", webElement != null);
		assertTrue("Movie list should be visible.", webElement.isDisplayed() );
		assertTrue("Movie selections should be enabled.", webElement.isEnabled());
    }
}
