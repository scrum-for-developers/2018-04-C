package de.codecentric.psd.worblehat.web.validation;

import de.codecentric.psd.worblehat.web.formdata.BookBorrowFormData;
import de.codecentric.psd.worblehat.web.formdata.BookDataFormData;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ISBNTrimFormDataTest {

    @Test
    public void TestISBNTrimmingInFormData() {

        BookBorrowFormData bookBorrowFormData = new BookBorrowFormData();
        BookDataFormData bookDataFormData = new BookDataFormData();

        String isbn10 = "014139739X";
        String isbn13 = "9780141397399";

        bookBorrowFormData.setIsbn(" " + isbn10 + " ");
        bookDataFormData.setIsbn(" " + isbn10 + " ");

        assertThat(isbn10, is(bookBorrowFormData.getIsbn()));
        assertThat(isbn10, is(bookDataFormData.getIsbn()));

        bookBorrowFormData.setIsbn(" " + isbn13 + " ");
        bookDataFormData.setIsbn(" " + isbn13 + " ");

        assertThat(isbn13, is(bookBorrowFormData.getIsbn()));
        assertThat(isbn13, is(bookDataFormData.getIsbn()));
    }

}
