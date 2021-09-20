package com.nguyenmauhuy.util;


import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    public static void put(String key, Object value, HttpServletRequest request){

        request.getSession().setAttribute(key,value);
    }

    public static void remove(String key, HttpServletRequest request){
        request.getSession().removeAttribute(key);
    }

    public static Object get(String key, HttpServletRequest request){
        return request.getSession().getAttribute(key);
    }
}
