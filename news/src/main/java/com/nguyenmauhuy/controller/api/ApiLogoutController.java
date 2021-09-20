package com.nguyenmauhuy.controller.api;

import com.nguyenmauhuy.controller.BaseController;
import com.nguyenmauhuy.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/logout")
public class ApiLogoutController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtil.remove("USER",req);

    }
}
