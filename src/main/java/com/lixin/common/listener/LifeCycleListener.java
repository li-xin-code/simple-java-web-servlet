package com.lixin.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author lixin
 */
@WebListener
public class LifeCycleListener implements ServletContextListener,
        HttpSessionListener, ServletRequestListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String contextName = event.getServletContext().getServletContextName();
        System.out.printf("ServletContext: %s,对象被创建了\n", contextName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        String contextName = event.getServletContext().getServletContextName();
        System.out.printf("ServletContext: %s,对象被创建了\n", contextName);
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        System.out.println("ServletRequest对象被创建了");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.println("ServletRequest对象被销毁了");
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println(event.getSession());
        System.out.println("HttpSession对象被创建了");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("HttpSession对象被销毁了");
    }

}