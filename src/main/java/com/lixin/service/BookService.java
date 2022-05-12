package com.lixin.service;

import com.lixin.model.entity.Book;

import java.util.List;

/**
 * @author lixin
 */
public interface BookService {

    /**
     * ...
     *
     * @param bookName ...
     */
    void add(String bookName);

    /**
     * all books.
     *
     * @return list
     */
    List<Book> list();

    /**
     * ...
     *
     * @param uuid ...
     */
    void remove(String uuid);

    /**
     * ...
     *
     * @param uuid ...
     * @param name ...
     */
    void rename(String uuid, String name);

}
