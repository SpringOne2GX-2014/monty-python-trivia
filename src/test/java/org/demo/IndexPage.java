package org.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public class IndexPage {
	
    private WebElement movie;

    private WebElement question;

    /**
     * Have WebDriver go to the index page, and return an 
     * object that represents this page in future tests.
     */
    public static IndexPage to(WebDriver driver) {
		//	The driver expects "http" to establish which protocol to use.  
		//	Don't know why it needs localhost.
		//	Also don't know why it needs the servlet mapping or how it determined 'mpt' since this is only known to maven or eclipse; it is not in any code.
        driver.get("http://localhost/mpt/");
        return PageFactory.initElements(driver, IndexPage.class);
    }
    
    
    /**
     * Obtain and return the # of options in the 'movie' selection element.
     */
    public int getNumberOfMovieOptions() {
		testElementBasics(movie);
    	Select select = new Select(movie);
    	return select.getOptions().size();
    }

    
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

    
    public void selectMovieOption(String movieOption) {
		this.selectOption(movie, movieOption);
    }
    
    
    public void selectQuestionOption(String question) {
		this.selectOption(movie, question);
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
