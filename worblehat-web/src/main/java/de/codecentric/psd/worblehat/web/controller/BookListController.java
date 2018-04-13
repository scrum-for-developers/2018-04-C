package de.codecentric.psd.worblehat.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;

/**
 * Controller class for the book table result.
 */
@Controller
@RequestMapping("/bookList")
public class BookListController {

    private BookService bookService;

    @Autowired
    public BookListController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(ModelMap modelMap) {
        List<Book> books = bookService.findAllBooks();
        for(Book book:books) {
            if (book.getDescription()!= null && book.getDescription().length() > 100) {
                book.setDescription(book.getDescription().substring(0, 99) + "...");
            }
        }
        modelMap.addAttribute("books", books);
        return "bookList";
    }

}
