package com.lixin.test;

import com.lixin.service.BookService;
import com.lixin.service.impl.BookServiceImpl;

/**
 * @author lixin
 */
public class BookServiceTest {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        
        System.out.println(bookService.list());
    }
}
