package com.lixin.service.impl;

import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.SystemUtils;
import com.lixin.dao.BookDao;
import com.lixin.dao.impl.BookDaoImpl;
import com.lixin.model.entity.Book;
import com.lixin.service.BookService;

import java.util.List;

/**
 * @author lixin
 */
public class BookServiceImpl implements BookService {

    private final BookDao bookDao = BookDaoImpl.getBookDao();

    @Override
    public void add(String bookName) {
        Book byName = bookDao.findByName(bookName);
        if (byName != null) {
            throw new NotExpectedException("book name already exists.");
        }
        Book book = new Book();
        book.setUuid(SystemUtils.uuid());
        book.setName(bookName);
        bookDao.insert(book);
    }

    @Override
    public List<Book> list() {
        return bookDao.all();
    }

    @Override
    public void remove(String uuid) {
        bookDao.del(uuid);
    }

    @Override
    public void rename(String uuid, String name) {
        bookDao.update(uuid, name);
    }
}
