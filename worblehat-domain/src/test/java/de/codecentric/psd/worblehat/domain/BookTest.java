package de.codecentric.psd.worblehat.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

    Book BOOK;

    @Before
    public void setup() {
        BOOK = new Book("Titel", "Author", "2", "1", 1234, "Description");
    }

    @Test
    public void shouldReturnFalseWhenAuthorisDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(),
            BOOK.getYearOfPublication(), BOOK.getDescription());
        anotherCopy.setAuthor("Bene");
        assertThat(BOOK.isSameCopy(anotherCopy), is(false));
    }

    @Test
    public void shouldReturnFalseWhenTitleisDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(),
            BOOK.getYearOfPublication(), BOOK.getDescription());
        anotherCopy.setTitle("Lord of the Rings");
        assertThat(BOOK.isSameCopy(anotherCopy), is(false));
    }

    @Test
    public void shouldReturnFalseWhenEditionisDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(),
            BOOK.getYearOfPublication(), BOOK.getDescription());
        anotherCopy.setEdition("2000");
        assertThat(BOOK.isSameCopy(anotherCopy), is(false));
    }

    @Test
    public void shouldReturnTrueWhenAllButTitleAndAuthorAndEditionAreDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(),
            BOOK.getYearOfPublication(), BOOK.getDescription());
        anotherCopy.setIsbn("123456789X");
        anotherCopy.setYearOfPublication(2010);
        assertThat(BOOK.isSameCopy(anotherCopy), is(true));
    }

    @Test
    public void shouldBeBorrowable() {
        BOOK.borrowNowByBorrower("a@bc.de");
        assertThat(BOOK.getBorrowing().getBorrowerEmailAddress(), is("a@bc.de"));
    }

    @Test
    public void shouldIgnoreNewBorrowWhenBorrowed() {
        BOOK.borrowNowByBorrower("a@bc.de");
        BOOK.borrowNowByBorrower("a@bc.ru");
        assertThat(BOOK.getBorrowing().getBorrowerEmailAddress(), is("a@bc.de"));
    }

    @Test
    public void knowsThatItIsNotBorrowedWhenNotBorrowed() {
        assertThat(BOOK.isBorrowed(), is(false));
    }

    @Test
    public void knowsThatItIsBorrowedWhenBorrowed() {
        BOOK.borrowNowByBorrower("a@bc.de");
        assertThat(BOOK.isBorrowed(), is(true));
    }

}