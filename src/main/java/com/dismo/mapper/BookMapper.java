package com.dismo.mapper;

import com.dismo.model.novel.NovelInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Edwin Yang on 2017/11/17 0017.
 */
@Mapper
public interface BookMapper {

   @Insert("INSERT INTO book(id,book_title,book_category,book_author,update_date,book_size,book_summary) " +
           "VALUES (#{id}, #{bookTitle}, #{bookCategory},#{bookAuthor},#{updateDate},#{bookSize},#{bookSummary})")
   int saveBook(NovelInfo bookBao);
}
