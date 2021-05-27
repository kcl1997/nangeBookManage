package com.kcl.controller;

import com.github.pagehelper.PageInfo;
import com.kcl.pojo.Book;
import com.kcl.pojo.Result;
import com.kcl.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 项目名： nange_book
 * 包名:    com.kcl.controller
 * 文件名   BookController
 * 创建者
 * 创建时间: 2021/5/25 9:29 AM
 * 描述  ${TODO}
 */

@RestController
public class BookController {

    @Autowired
    BookService mBookService;

    @GetMapping("/book/get/{pageNum}/{pageSize}")
    public PageInfo<Book> getBooks(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize")Integer pageSize){
        return mBookService.getAllBooks(pageNum,pageSize);
    }

    /**
     * 必须使用 @RequestBody 才能将Json -> book对象， 自动填充
     */
    @PostMapping("/book/add")
    public Result addBook(@RequestBody  @Valid Book book){

        try {
            mBookService.addBook(book);
            return Result.success(book);
        } catch (Exception e) {
            return Result.error(400,"添加失败");
        }
    }


    @PostMapping("/book/update")
    public Result updateBook(@RequestBody @Valid Book book){
        try {
            mBookService.updateBook(book);
            return Result.success(book);
        } catch (Exception e) {
            return Result.error(400,"更新失败");
        }
    }

    @DeleteMapping("/book/delete/{id}")
    public Result deleteBook(@PathVariable("id")Integer id){
        try {
            mBookService.deleteBook(id);
            return Result.success("删除成功",null);
        } catch (Exception e) {
            return Result.error(400,"删除失败");
        }

    }

}

