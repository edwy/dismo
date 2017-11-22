package com.dismo.model.novel;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author  Edwin Yang on
 * 2017/11/16 0016.
 */
@TargetUrl("http://www\\.bookbao\\.cc/TXT/down_\\w+\\.html")
@HelpUrl("http://www\\.bookbao\\.cc/TXT/list2_[0-9]+\\.html")
public class NovelDetail implements AfterExtractor {

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

    @Override
    public void afterProcess(Page page) {

    }
}
