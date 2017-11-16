package com.dismo.service;


import com.dismo.model.BookDetail;
import com.dismo.model.BookInfo;

/**
 * @author Yang Jiyu
 */
public interface BookSpiderService {

    String saveBook(BookInfo bookBao);

    String selIdByBookName(String bookName);

    String saveBookDetail(BookDetail bookDetail);

}
