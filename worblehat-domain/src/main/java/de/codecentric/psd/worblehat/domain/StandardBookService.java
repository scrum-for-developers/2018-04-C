package de.codecentric.psd.worblehat.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** The domain service class for book operations. */
@Service
@Transactional
public class StandardBookService implements BookService {

  private BorrowingRepository borrowingRepository;
  private BookRepository bookRepository;

  public StandardBookService() {
    // Do nothing because of other Constructor
  }

  @Autowired
  public StandardBookService(
      BorrowingRepository borrowingRepository, BookRepository bookRepository) {
    this.borrowingRepository = borrowingRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public List<Borrowing> getAllBooksByBorrower(String borrowerEmailAddress) {
    List<Borrowing> borrowingsByUser =
        borrowingRepository.findBorrowingsByBorrower(borrowerEmailAddress);
    return borrowingsByUser;
  }

  @Override
  public void returnAllBooksByBorrower(String borrowerEmailAddress) {
    List<Borrowing> borrowingsByUser =
        borrowingRepository.findBorrowingsByBorrower(borrowerEmailAddress);
    for (Borrowing borrowing : borrowingsByUser) {
      borrowingRepository.delete(borrowing);
    }
  }

  @Override
  public void returnSingleBookByBorrower(String borrowerEmailAddress, String isbn, String title) {
    List<Borrowing> borrowingsByUser =
        borrowingRepository.findBorrowingsByBorrower(borrowerEmailAddress);
    for (Borrowing borrowing : borrowingsByUser) {
      if ((isbn != null && borrowing.getBorrowedBook().getIsbn().equals(isbn))
          || (title != null && borrowing.getBorrowedBook().getTitle().equals(title))) {
        borrowingRepository.delete(borrowing);
      }
    }
  }

  @Override
  public Optional<Borrowing> borrowBook(String isbn, String borrower) {
    Set<Book> books = bookRepository.findByIsbn(isbn);

    Optional<Book> unborrowedBook =
        books.stream().filter(book -> book.getBorrowing() == null).findFirst();

    return unborrowedBook.map(
        book -> {
          book.borrowNowByBorrower(borrower);
          borrowingRepository.save(book.getBorrowing());
          return book.getBorrowing();
        });
  }

  @Override
  public Set<Book> findBooksByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn); // null if not found
  }

  @Override
  public List<Book> findAllBooks() {
    return bookRepository.findAllByOrderByTitle();
  }

  @Override
  public Optional<Book> createBook(
      @Nonnull String title,
      @Nonnull String author,
      @Nonnull String edition,
      @Nonnull String isbn,
      int yearOfPublication,
      @Nonnull String description) {
    Book book = new Book(title, author, edition, isbn, yearOfPublication, description);

    Optional<Book> bookFromRepo = bookRepository.findTopByIsbn(isbn);

    if (!bookFromRepo.isPresent() || book.isSameCopy(bookFromRepo.get())) {
      return Optional.of(bookRepository.save(book));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Book> findBookById(String id) {
    return bookRepository.findById(Long.parseLong(id));
  }

  @Override
  public boolean bookExists(String isbn) {
    Set<Book> books = bookRepository.findByIsbn(isbn);
    return !books.isEmpty();
  }

  @Override
  public void deleteAllBooks() {
    borrowingRepository.deleteAll();
    bookRepository.deleteAll();
  }

  @Override
  public Set<Book> findBooksByEdition(String edition) {
    return bookRepository.findByEdition(edition); // null if not found
  }

  @Override
  public void deleteBook(Book book) {
    if (!book.isBorrowed()) {
      bookRepository.delete(book);
    }
  }
}
