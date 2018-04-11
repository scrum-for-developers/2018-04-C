package de.codecentric.worblehat.acceptancetests.adapter.wrapper;

public enum PageElement {
    ADDBOOKBUTTON("addBook"), BOOKLIST("bookList"), BORROWBOOKBUTTON("borrowBook"), ISBNERROR("isbn-error"),
    RETURNSINGLEBOOKBUTTON("returnSingleBook"), RETURNALLBOOKSBUTTON("returnAllBooks"), ERROR("error");

    private String elementId;

    PageElement(String elementId) {
        this.elementId = elementId;
    }

    public String getElementId() {
        return elementId;
    }
}
