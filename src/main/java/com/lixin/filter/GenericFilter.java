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
    public void init(FilterConfig filterConfig) {
        System.out.println("init: " + filterConfig.getFilterName());
        Enumeration<String> names = filterConfig.getInitParameterNames();
        if (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println(name + ":" + filterConfig.getInitParameter(name));
        }
    }

    /**
     * filter
     *
     * @param request  request
     * @param response response
     * @param chain    chain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public abstract void doFilter(ServletRequest request,
                                  ServletResponse response, FilterChain chain)
            throws IOException, ServletException;

    @Override
    public void destroy() {

    }

}
