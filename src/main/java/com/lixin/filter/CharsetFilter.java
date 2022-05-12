package com.lixin.filter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author lx
 * @date 2022/4/5
 */
@WebFilter(filterName = "charsetFilter", urlPatterns = "/*")
public class CharsetFilter extends GenericFilter {

    private final static String UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        chain.doFilter(request, response);
    }

}
