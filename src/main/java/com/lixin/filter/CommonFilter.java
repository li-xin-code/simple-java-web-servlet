package com.lixin.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lixin
 */
@WebFilter(filterName = "commonFilter", urlPatterns = "/*")
public class CommonFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        System.out.println("SessionId : " + session.getId());
        System.out.println("current user : " + session.getAttribute("user"));
        chain.doFilter(request, response);
    }
}
