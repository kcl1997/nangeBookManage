package com.kcl.dao;

import com.kcl.pojo.Book;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 项目名： nange_book
 * 包名:    com.kcl.dao
 * 文件名   BookDao
 * 创建者
 * 创建时间: 2021/5/25 9:27 AM
 * 描述  ${TODO}
 */

@Mapper
public interface BookDao {


    @Select("select * from book")
    List<Book> getAllBooks();

    @Insert("insert into book(name,author,publish,pages,price) values(#{name},#{author},#{publish},#{pages},#{price})")
    void addBook(Book book);


    //根据id修改book
    @Update("update book set name=#{name},author=#{author},publish=#{publish},pages=#{pages},price=#{price} where id = #{id}")
    void updateBook(Book book);

    @Delete("delete from book where id = #{id}")
    void deleteBook(Integer id);
}
