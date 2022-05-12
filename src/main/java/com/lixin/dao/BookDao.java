package com.lixin.dao;

import com.lixin.model.entity.Book;

import java.util.List;

/**
 * @author lixin
 */
public interface BookDao {

    /**
     * ...
     *
     * @param book ...
     */
    void insert(Book book);

    /**
     * ...
     *
     * @return ...
     */
    List<Book> all();

    /**
     * ...
     *
     * @param name ...
     * @return ...
     */
    Book findByName(String name);

    /**
     * ...
     *
     * @param id ...
     * @return ...
     */
    Book findById(String id);

    /**
     * ...
     *
     * @param uuid ...
     */
    void del(String uuid);

    /**
     * ...
     *
     * @param uuid ...
     * @param name ...
     */
    void update(String uuid, String name);

}
