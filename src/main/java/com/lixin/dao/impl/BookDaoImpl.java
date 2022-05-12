package com.lixin.dao.impl;

import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.JdbcUtils;
import com.lixin.dao.BookDao;
import com.lixin.model.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public void insert(Book book) {
        String sql = "insert into book(name,uuid) value(?,?)";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement pre = null;
        try {
            assert connection != null;
            pre = connection.prepareStatement(sql);
            pre.setString(1, book.getName());
            pre.setString(2, book.getUuid());
            int rows = pre.executeUpdate();
            if (rows != 1) {
                throw new NotExpectedException("sql execution not as expected: row count != 1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert pre != null;
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Book> all() {
        ArrayList<Book> books = new ArrayList<>();
        Connection connection = JdbcUtils.getConnection();
        String sql = "select name,uuid from book";
        ResultSet res = null;
        PreparedStatement pre = null;
        try {
            assert connection != null;
            pre = connection.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Book book = new Book();
                book.setName(res.getString(1));
                book.setUuid(res.getString(2));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            assert res != null;
            close(connection, res, pre);
        }
        return books;
    }

    @Override
    public void del(String uuid) {
        String sql = "delete from book where uuid = ?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement pre = null;
        try {
            assert connection != null;
            pre = connection.prepareStatement(sql);
            pre.setString(1, uuid);
            int rows = pre.executeUpdate();
            if (rows != 1) {
                throw new NotExpectedException("sql execution not as expected: row count != 1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert pre != null;
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String uuid, String name) {
        String sql = "update book set name = ? where uuid = ?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement pre = null;
        try {
            assert connection != null;
            pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, uuid);
            int rows = pre.executeUpdate();
            if (rows != 1) {
                throw new NotExpectedException("sql execution not as expected: row count != 1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert pre != null;
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Book findByName(String name) {
        String sql = "select name,uuid from book where name = ?";
        Book book = null;
        try {
            book = one(sql, name);
        } catch (NotExpectedException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book findById(String id) {
        String sql = "select name,uuid from book where uuid = ? limit 1;";
        Book book = null;
        try {
            book = one(sql, id);
        } catch (NotExpectedException e) {
            e.printStackTrace();
        }
        return book;
    }

    private Book one(String sql, String arg) {
        Book book = new Book();
        Connection connection = JdbcUtils.getConnection();
        ResultSet res = null;
        PreparedStatement pre = null;
        try {
            assert connection != null;
            pre = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre.setString(1, arg);
            res = pre.executeQuery();
            if (res.first()) {
                book.setName(res.getString(1));
                book.setUuid(res.getString(2));
            } else {
                throw new NotExpectedException("sql execution not as expected : not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            assert res != null;
            close(connection, res, pre);
        }
        return book;
    }

    private void close(Connection connection, ResultSet res, PreparedStatement pre) {
        try {
            assert connection != null;
            connection.close();
            assert pre != null;
            pre.close();
            assert res != null;
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
