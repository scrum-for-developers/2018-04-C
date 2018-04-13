package de.codecentric.psd.worblehat.web.formdata;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import de.codecentric.psd.worblehat.web.validation.ISBN;
import de.codecentric.psd.worblehat.web.validation.Numeric;

/**
 * This class represent the form data of the add book form.
 */
public class BookDataFormData {

    @NotEmpty
    private String title;

    @NotEmpty
    @Numeric
    private String edition;

    @NotEmpty
    @Numeric
    @Length(message = "{bookDataFormData.yearOfPublication.length.invalid}", min = 4, max = 4)
    private String yearOfPublication;

    @NotEmpty
    @ISBN
    private String isbn;

    @NotEmpty
    private String author;

    private String description;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "BookDataFormData [title=" + title + ", edition=" + edition + ", yearOfPublication=" + yearOfPublication
            + ", isbn=" + isbn + ", author=" + author + ", description=" + description + "]";
    }

}
