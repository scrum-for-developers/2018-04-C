package de.codecentric.psd.worblehat.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.domain.Borrowing;
import de.codecentric.psd.worblehat.web.formdata.BookBorrowListFormData;

/**
 * Controller for BorrowingBook
 */
@RequestMapping("/borrowList")
@Controller
public class BorrowBookListController {

    private BookService bookService;

    private static final String BORROWLIST = "borrowList";

    @Autowired
    public BorrowBookListController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void setupForm(final ModelMap model) {
        model.put("borrowListFormData", new BookBorrowListFormData());
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(final ModelMap model,
            @ModelAttribute("borrowListFormData") @Valid BookBorrowListFormData borrowFormData, BindingResult result) {
        if (result.hasErrors()) {
            return BORROWLIST;
        }
        List<Borrowing> borrowList = bookService.getAllBooksByBorrower(borrowFormData.getEmail());
        model.put(BORROWLIST, borrowList);

        return BORROWLIST;
    }

    @ExceptionHandler(Exception.class)
    public String handleErrors(Exception ex, HttpServletRequest request) {
        return "home";
    }
}
