package org.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class IndexPage {
	
    private WebElement movie;

    private WebElement question;

    public static IndexPage to(WebDriver driver) {
        driver.get("http://localhost/mpt/");
        return PageFactory.initElements(driver, IndexPage.class);
    }
    
    public int getNumberOfMovieOptions() {
    	Select select = new Select(movie);
    	int i = select.getOptions().size();
    	return i;
    }
}
