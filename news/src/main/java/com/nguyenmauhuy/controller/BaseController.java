package com.nguyenmauhuy.controller;

import com.google.gson.Gson;
import com.nguyenmauhuy.exception.InternalServerException;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class BaseController extends HttpServlet {
    protected static Gson gson;

    public BaseController(){
        this.gson = new Gson();
    }

    public <T> T mapToModel(HttpServletRequest request, Class<T> clazz) {
        try {
            T t = clazz.getDeclaredConstructor().newInstance();
           BeanUtils.populate(t, request.getParameterMap());

            return t;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new InternalServerException(e.getCause().toString());
        }
    }

    public static <T> T convertToModel(HttpServletRequest request, Class<T> tClass){

        StringBuilder json = new StringBuilder();
        String line;
        try{
            while ((line = request.getReader().readLine()) != null){
                json.append(line);
            }
            return gson.fromJson(json.toString(),tClass);
        }catch (IOException e){

            e.printStackTrace();

            return null;
        }
    }

    public void responseClinet(HttpServletResponse resp, Object model) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(model));
        out.flush();
    }



}
