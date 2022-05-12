package com.lixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lx
 * @date 2022/3/31
 */
@WebServlet(name = "numberGuessGamesServlet", urlPatterns = "/number-guessing-games")
public class NumberGuessGamesServlet extends HttpServlet {

    private static final String METHOD_GET = "GET";
    private static final long serialVersionUID = -6214262480134499398L;
    Map<String, Integer> map = new HashMap<>(16);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.service(req, resp);
        if (METHOD_GET.equalsIgnoreCase(req.getMethod())) {
            printMap();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int guess;
        try {
            guess = Integer.parseInt(req.getParameter("guess"));
        } catch (NumberFormatException e) {
            resp.getWriter().println("Please enter a number");
            return;
        }
        String sessionId = req.getSession().getId();
        System.out.println("current session id :" + sessionId);
        if (!map.containsKey(sessionId)) {
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            map.put(sessionId, num);
        }
        Integer target = map.get(sessionId);
        if (target == guess) {
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            map.put(sessionId, num);
            resp.getWriter().println("succeed");
            return;
        }
        String result = guess < target ? "smaller" : "bigger";
        resp.getWriter().print(result);
    }

    protected void printMap() {
        System.out.println("=====View Map Start=====");
        map.keySet().forEach(key -> System.out.println(key + ":" + map.get(key)));
        System.out.println("=====View Map End=====");
    }
}
