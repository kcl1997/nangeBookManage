package com.kcl;

import com.kcl.pojo.Book;
import com.kcl.service.BookService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NangeBookApplicationTests {

    @Autowired
    BookService mBookService;

    @Test
    void contextLoads() {
        Book book = new Book();
        book.setId(106);
        book.setAuthor("图书名称106");
        book.setName("图书作者01");
        book.setPages(100);
        book.setPublish("图书出版社01");
        book.setPrice(29.9f);
        mBookService.updateBook(book);


    }

}
