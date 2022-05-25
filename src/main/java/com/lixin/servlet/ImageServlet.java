package com.lixin.servlet;

import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.SystemUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.lixin.common.utils.SystemUtils.CONTENT_TYPE_MAP;
import static com.lixin.common.utils.SystemUtils.UPLOAD_PATH;

/**
 * @author lixin
 */
@WebServlet(name = "imageServlet", urlPatterns = ImageServlet.URL_PATTERNS + "*")
public class ImageServlet extends HttpServlet {

    public static final String URL_PATTERNS = "/ui/";

    private static final long serialVersionUID = -372121073383305839L;

    private final String DEFAULT = "";
    private final Map<String, Function<List<String>, Boolean>> dispatcher = new HashMap<>();

    @Override
    public void init() {
        dispatcher.put(DEFAULT, list -> list.size() == 1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(DEFAULT).apply(parse)) {
            String filename = parse.get(0);
            String suffix = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            if (!CONTENT_TYPE_MAP.containsKey(suffix)) {
                throw new NotExpectedException("Unsupported file type.");
            }
            File file = new File(UPLOAD_PATH + filename);

            if (!file.exists()) {
                throw new NotExpectedException("File is not find.");
            }
            FileInputStream inputStream = new FileInputStream(file);
            resp.reset();
            resp.setContentType(CONTENT_TYPE_MAP.get(suffix));
            ServletOutputStream outputStream = resp.getOutputStream();
            SystemUtils.inputToOutPut(inputStream, outputStream);
        }
    }

    private List<String> parse(String uri) {
        String prefix = "/ui/";
        String substring = uri.substring(uri.indexOf(prefix) + prefix.length());
        String[] split = substring.split("/");
        return Arrays.asList(split);
    }
}
