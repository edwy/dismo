package com.dismo.service.impl;


import com.dismo.model.BookDetail;
import com.dismo.model.BookInfo;
import com.dismo.service.BookSpiderService;
import org.springframework.stereotype.Service;

/**
 * @author Yang Jiyu
 */
@Service
public class BookSpiderServiceImpl implements BookSpiderService {
    /**
     * 按页保存小说主表;
     */
    @Override
    public String saveBook(BookInfo bookBao) {
        return null;
    }

    /**
     * 根据小说名称获取主键;
     */
    @Override
    public String selIdByBookName(String bookName) {
        return null;
    }

    /**
     * 按页保存小说明细;
     */
    @Override
    public String saveBookDetail(BookDetail bookDetail) {
        return null;
    }
}
