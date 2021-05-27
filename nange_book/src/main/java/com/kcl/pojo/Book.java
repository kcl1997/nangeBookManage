package com.kcl.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： nange_book
 * 包名:    com.kcl.pojo
 * 文件名   Book
 * 创建者
 * 创建时间: 2021/5/25 9:24 AM
 * 描述  ${TODO}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;


    @NotBlank(message = "书名不能为空")
    private String name;
    @NotBlank(message = "作者不能为空")
    private String author;
    @NotBlank(message = "出版社不能为空")
    private String publish; //出版社


    // Integer会有json转换越界问题
    @NotNull(message = "页数不能为空")
    @Min(value = 1,message = "页数至少为1")
    @Max(value = 1000000, message = "页数最多一百万！")
    private Integer pages; //页数


    @NotNull(message = "价格不能为空")
    @Min(value = 0,message = "价格必须大于等于0")
    @Max(value = 2147483647, message = "价格最多一百万！")
    private Float price; //价格

    private Integer bookcaseid;
    private Integer abled;
}

