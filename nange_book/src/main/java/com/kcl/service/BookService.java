package com.kcl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kcl.dao.BookDao;
import com.kcl.pojo.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名： nange_book
 * 包名:    com.kcl.service
 * 文件名   BookService
 * 创建者
 * 创建时间: 2021/5/25 9:28 AM
 * 描述  ${TODO}
 */

@Service
public class BookService {


    @Autowired
    BookDao mBookDao;

    @Transactional
    public PageInfo<Book> getAllBooks(Integer pageNum,Integer PageSize){

        //汉堡包夹击
        PageHelper.startPage(pageNum,PageSize);
        List<Book> list = mBookDao.getAllBooks();
        PageInfo<Book> books = new PageInfo<>(list);

        return books;
    }

    @Transactional
    public void addBook(Book book){
        mBookDao.addBook(book);
    }


    @Transactional
    public void updateBook(Book book){
        mBookDao.updateBook(book);
    }

    @Transactional
    public void deleteBook(Integer id){
        mBookDao.deleteBook(id);
    }

}

