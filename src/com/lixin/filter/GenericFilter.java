package com.lixin.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author lx
 * @date 2022/4/5
 */
public abstract class GenericFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init: " + filterConfig.getFilterName());
        Enumeration<String> names = filterConfig.getInitParameterNames();
        if (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println(name + ":" + filterConfig.getInitParameter(name));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

}
