package com.dismo.model;

import javax.persistence.Id;

/**
 * Created by Edwin Yang on 2017/11/16 0016.
 */
public class BookDetail {

    @Id
    private String id;
    private String bookId;
    private String bookContent;
    private String bookIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public String getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(String bookIndex) {
        this.bookIndex = bookIndex;
    }
}
