package com.lixin.test;

import com.lixin.dao.BookDao;
import com.lixin.dao.impl.BookDaoImpl;

/**
 * @author lixin
 */
public class BookDaoTest {
    public static void main(String[] args) {
        BookDao bookDao = BookDaoImpl.getBookDao();
        System.out.println(bookDao.findById("FSHsQcmg"));
        System.out.println(bookDao.findByName("Java编程思想"));
        System.out.println(bookDao.all());
    }
}
