package com.lixin.dao.impl;

import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.DbUtils;
import com.lixin.dao.BookDao;
import com.lixin.model.entity.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author lixin
 */
public class BookDaoImpl implements BookDao {

    private static final class BookDaoHolder {
        static final BookDao BOOK_DAO = new BookDaoImpl();
    }

    public static BookDao getBookDao() {
        return BookDaoHolder.BOOK_DAO;
    }

    private final QueryRunner runner = DbUtils.getRunner();

    @Override
    public void insert(Book book) {
        String sql = "insert into book(name,uuid) value(?,?)";
        try {
            runner.update(sql, book.getName(), book.getUuid());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotExpectedException(e.getMessage());
        }
    }

    @Override
    public List<Book> all() {
        String sql = "select name,uuid from book";
        try {
            return runner.query(sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotExpectedException(e.getMessage());
        }
    }

    @Override
    public void del(String uuid) {
        String sql = "delete from book where uuid = ?";
        try {
            runner.update(sql, uuid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotExpectedException(e.getMessage());
        }
    }

    @Override
    public void update(String uuid, String name) {
        String sql = "update book set name = ? where uuid = ?";
        try {
            runner.update(sql, name, uuid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotExpectedException(e.getMessage());
        }
    }

    @Override
    public Book findByName(String name) {
        String sql = "select name,uuid from book where name = ?";
        return one(sql, name);
    }

    @Override
    public Book findById(String id) {
        String sql = "select name,uuid from book where uuid = ? limit 1;";
        return one(sql, id);
    }

    private Book one(String sql, String arg) {
        Book book = null;
        try {
            book = runner.query(sql, new BeanHandler<>(Book.class), arg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

}
