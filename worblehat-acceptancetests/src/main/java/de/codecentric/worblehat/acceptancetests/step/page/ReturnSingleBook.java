package de.codecentric.worblehat.acceptancetests.step.page;

import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.codecentric.worblehat.acceptancetests.adapter.SeleniumAdapter;
import de.codecentric.worblehat.acceptancetests.adapter.wrapper.Page;
import de.codecentric.worblehat.acceptancetests.adapter.wrapper.PageElement;

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

    @When("borrower $borrower returns a single book")
    public void whenUseruserReturnsSingleBook(String borrower1, String isbn, String title) {
        seleniumAdapter.gotoPage(Page.RETURNSINGLEBOOK);
        seleniumAdapter.typeIntoField("emailAddress", borrower1);
        seleniumAdapter.typeIntoField("isbn", isbn);
        seleniumAdapter.typeIntoField("title", title);
        seleniumAdapter.clickOnPageElement(PageElement.RETURNSINGLEBOOKBUTTON);
    }

}
