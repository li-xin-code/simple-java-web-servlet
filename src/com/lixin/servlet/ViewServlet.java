package com.lixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 * @date 2022/4/5
 */
@WebServlet(name = "viewServlet", urlPatterns = "/view/*")
public class ViewServlet extends HttpServlet {
    private final static String PREFIX = "/page/";
    private final static String SUFFIX = ".html";
    private final static String SPLIT_CHAR = "/";
    private final static int PATH_LEN = 3;
    private final Map<String, String> viewMap = new HashMap<>(16);

    @Override
    public void init() throws ServletException {
        String path = null;
        try {
            URL url = getServletContext().getResource(PREFIX);
            path = url.getFile().substring(1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert path != null;
        File file = new File(path);
        File[] files = file.listFiles(File::isFile);
        assert files != null;
        Arrays.stream(files).forEach((f -> {
            String fillFileName = f.getName();
            String mainFileName = fillFileName.substring(0, fillFileName.lastIndexOf("."));
            viewMap.put(mainFileName, PREFIX + fillFileName);
        }));
        viewMap.keySet().forEach(s -> System.out.println(s + ":" + viewMap.get(s)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String uri = req.getRequestURI();
        String[] paths = uri.substring(1).split(SPLIT_CHAR);
        if (paths.length != PATH_LEN) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String viewName = paths[paths.length - 1];
        String viewPath = viewMap.get(viewName);

        InputStream in = null;
        try {
            in = getServletContext().getResourceAsStream(viewPath);
            OutputStream out = resp.getOutputStream();
            inputToOutPut(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
    }

    private void inputToOutPut(InputStream in, OutputStream out) {
        try {
            int read;
            while ((read = in.read()) != -1) {
                out.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
