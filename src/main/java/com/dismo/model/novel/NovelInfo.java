package com.dismo.model.novel;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author Yang Jiyu
 *         Date: 17-11-17
 *         Time: 下午4:28
 */
@TargetUrl("http://www\\.bookbao\\.cc/TXT/down_\\w+\\.html")
@HelpUrl("http://www\\.bookbao\\.cc/TXT/list2_[0-9]+\\.html")
public class NovelInfo implements AfterExtractor {

    private String id;

    @ExtractBy(value = ".pml1 >h1" ,type = ExtractBy.Type.Css)
    private String book_title;

    @ExtractBy("//*[@id=\"xxlist\"]/li[2]/text()")
    private String book_category;

    @ExtractBy("//*[@id=\"xxlist\"]/li[5]/text()")
    private String book_author;

    @ExtractBy("//*[@id=\"xxlist\"]/li[7]/text()")
    private String update_date;

    @ExtractBy("//*[@id=\"xxlist\"]/li[4]/text()")
    private String book_size;

    @ExtractBy(value=".con_text > li > span" ,type = ExtractBy.Type.Css)
    private String book_summary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_category() {
        return book_category;
    }

    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getBook_size() {
        return book_size;
    }

    public void setBook_size(String book_size) {
        this.book_size = book_size;
    }

    public String getBook_summary() {
        return book_summary;
    }

    public void setBook_summary(String book_summary) {
        this.book_summary = book_summary;
    }

    @Override
    public void afterProcess(Page page) {

    }
}
