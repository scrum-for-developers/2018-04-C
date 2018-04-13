package de.codecentric.psd.worblehat.web.controller;


import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.web.formdata.BookBorrowFormData;
import de.codecentric.psd.worblehat.web.formdata.BookDeleteFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/bookDelete")
public class BookDeleteController {

    private BookService bookService;

    @Autowired
    public BookDeleteController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/{bookId}", method = RequestMethod.GET)
    public String viewDeleteBook(final ModelMap modelMap, @PathVariable String bookId) {
        Optional<Book> book = this.bookService.findBookById(bookId);
        BookDeleteFormData bookDeleteFormData = new BookDeleteFormData();
        bookDeleteFormData.setId(bookId);
        modelMap.put("isbn", book.get().getIsbn());
        modelMap.put("bookDeleteFormData", bookDeleteFormData);
        return "bookDelete";
    }

    @RequestMapping(value="/{bookId}", method = RequestMethod.POST)
    public String deleteBook(@PathVariable String bookId, @ModelAttribute("bookDeleteFormData") @Valid BookDeleteFormData bookDeleteFormData) {
        // delete book
        this.bookService.deleteBook(this.bookService.findBookById(bookId).get());
        return "home";
    }

}
