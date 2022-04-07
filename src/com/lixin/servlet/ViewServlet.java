package com.lixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 响应页面请求
 * 初始化阶段扫描 {web/page/}目录加载页面文件
 * 文件名和路径存入viewMap
 *
 * @author lx
 * @date 2022/4/5
 */
@WebServlet(name = "viewServlet", urlPatterns = "/view/*")
public class ViewServlet extends HttpServlet {
    private final static String PREFIX = "/page/";
    private final static String SPLIT_CHAR = "/";
    private final static int PATH_LEN = 3;
    private static final long serialVersionUID = 1458049632137078049L;
    private final Map<String, String> viewMap = new HashMap<>(16);

    protected Set<String> getViewList() {
        return viewMap.keySet();
    }

    @Override
    public void init() {
        String path = null;
        try {
            URL url = getServletContext().getResource(PREFIX);
            path = url.getFile();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert path != null;
        File file = new File(path);
        System.out.println("Scan: " + file);
        System.out.println(Arrays.toString(file.list()));
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
            throws IOException, ServletException {
        String uri = req.getRequestURI();
        String[] paths = uri.substring(1).split(SPLIT_CHAR);
        if (paths.length != PATH_LEN) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        String viewName = paths[paths.length - 1];
        String viewPath = viewMap.get(viewName);

        String regex = "^.+\\.jsp$";
        boolean isJspFile = Pattern.matches(regex, viewPath);
        if (isJspFile) {
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }

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
