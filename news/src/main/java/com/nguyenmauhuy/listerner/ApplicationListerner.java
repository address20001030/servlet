package com.nguyenmauhuy.listerner;

import com.nguyenmauhuy.bean.BeanFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListerner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       BeanFactory beanFactory = new BeanFactory();
    }
}
