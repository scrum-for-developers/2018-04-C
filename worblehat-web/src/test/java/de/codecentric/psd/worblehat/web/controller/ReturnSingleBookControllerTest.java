package de.codecentric.psd.worblehat.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.web.formdata.ReturnSingleBookFormData;

public class ReturnSingleBookControllerTest {

    private ReturnSingleBookController returnSingleBookController;

    private BookService bookService;

    private ReturnSingleBookFormData returnSingleBookFormData;

    private BindingResult bindingResult;

    @Before
    public void setUp() throws Exception {
        bookService = mock(BookService.class);
        returnSingleBookController = new ReturnSingleBookController(bookService);
        returnSingleBookFormData = new ReturnSingleBookFormData();
        bindingResult = new MapBindingResult(new HashMap<>(), "");
    }

    @Test
    public void shouldSetupForm() throws Exception {
        ModelMap modelMap = new ModelMap();

        returnSingleBookController.prepareView(modelMap);

        assertThat(modelMap.get("returnSingleBookFormData"), is(not(nullValue())));
    }

    @Test
    public void shouldRejectErrors() throws Exception {
        bindingResult.addError(new ObjectError("", ""));

        String navigateTo = returnSingleBookController.returnSingleBook(returnSingleBookFormData, bindingResult);

        assertThat(navigateTo, is("returnSingleBook"));
    }

    @Test
    public void shouldReturnASingleBookByIsbnAndNavigateHome() throws Exception {
        String borrower = "someone@codecentric.de";
        returnSingleBookFormData.setEmailAddress(borrower);

        String isbn = "123456789X";
        returnSingleBookFormData.setIsbn(isbn);

        String navigateTo = returnSingleBookController.returnSingleBook(returnSingleBookFormData, bindingResult);

        verify(bookService).returnSingleBookByBorrower(borrower, isbn, null);
        assertThat(navigateTo, is("home"));
    }
}
