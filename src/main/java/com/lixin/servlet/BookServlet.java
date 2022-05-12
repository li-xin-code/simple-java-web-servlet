package com.lixin.servlet;

import com.lixin.common.utils.json.JsonObject;
import com.lixin.common.utils.json.JsonUtils;
import com.lixin.model.entity.Book;
import com.lixin.service.BookService;
import com.lixin.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lixin
 */
@WebServlet(name = "bookServlet", urlPatterns = "/book")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = -3226400635834746093L;
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Book> books = bookService.list();
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject success = JsonUtils.httpSuccess(books);
        writer.print(success);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String uuid = (String) req.getAttribute("uuid");
        bookService.remove(uuid);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(JsonUtils.httpSuccess(null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = (String) req.getAttribute("name");
        bookService.add(name);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(JsonUtils.httpSuccess(null));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String uuid = (String) req.getAttribute("uuid");
        String name = (String) req.getAttribute("name");
        bookService.rename(uuid, name);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(JsonUtils.httpSuccess(null));
    }
}
