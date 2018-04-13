package de.codecentric.psd.worblehat.web.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.web.formdata.ReturnSingleBookFormData;

/**
 * Controller class for the
 */
@Controller
@RequestMapping("/returnSingleBook")
public class ReturnSingleBookController {

    private BookService bookService;

    @Autowired
    public ReturnSingleBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void prepareView(ModelMap modelMap) {
        modelMap.put("returnSingleBookFormData", new ReturnSingleBookFormData());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String returnSingleBook(@ModelAttribute("returnSingleBookFormData") @Valid ReturnSingleBookFormData formData,
            BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "returnSingleBook";
        } else {
            boolean deletable = false;
            List<Book> books = bookService.findAllBooks();
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getBorrowing() != null
                    && formData.getEmailAddress().equals(books.get(i).getBorrowing().getBorrowerEmailAddress())) {
                    if (formData.getIsbn() != null && formData.getIsbn().equals(books.get(i).getIsbn())) {
                        deletable = true;
                    }
                    if (formData.getTitle() != null && formData.getTitle().equals(books.get(i).getTitle())) {
                        deletable = true;
                    }
                }
            }

            if (deletable) {
                bookService.returnSingleBookByBorrower(formData.getEmailAddress(), formData.getIsbn(), formData.getTitle());
                return "home";
            } else {
                ReturnSingleBookFormData newFormData = new ReturnSingleBookFormData();
                newFormData.setEmailAddress(formData.getEmailAddress());
                newFormData.setIsbn(formData.getIsbn());
                newFormData.setTitle(formData.getTitle());
                newFormData.setNichtGefunden("Keine passenden Ausleihen gefunden");
                modelMap.put("returnSingleBookFormData", newFormData);
                return "returnSingleBook";
            }
        }
    }

}
