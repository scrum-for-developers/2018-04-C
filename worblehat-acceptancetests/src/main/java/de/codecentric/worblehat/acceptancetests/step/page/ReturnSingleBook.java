package de.codecentric.worblehat.acceptancetests.step.page;

import de.codecentric.worblehat.acceptancetests.adapter.SeleniumAdapter;
import de.codecentric.worblehat.acceptancetests.adapter.wrapper.Page;
import de.codecentric.worblehat.acceptancetests.adapter.wrapper.PageElement;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReturnSingleBook {
	private final SeleniumAdapter seleniumAdapter;

	@Autowired
	public ReturnSingleBook(SeleniumAdapter seleniumAdapter) {
		this.seleniumAdapter = seleniumAdapter;
	}
	
	// *******************
	// *** G I V E N *****
	// *******************

	// *****************
	// *** W H E N *****
	// *****************
	
	@When("borrower $borrower returns the book $isbn")
	public void whenUseruserReturnsAllHisBooks(String borrower1, String isbn) {
		seleniumAdapter.gotoPage(Page.RETURNSINGLEBOOK);
		seleniumAdapter.typeIntoField("emailAddress", borrower1);
		seleniumAdapter.typeIntoField("isbn", isbn);
		seleniumAdapter.clickOnPageElement(PageElement.RETURNSINGLEBOOKBUTTON);
	}

}
