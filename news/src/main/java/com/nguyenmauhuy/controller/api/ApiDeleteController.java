package com.nguyenmauhuy.controller.api;


import com.nguyenmauhuy.bean.BeanFactory;
import com.nguyenmauhuy.controller.BaseController;
import com.nguyenmauhuy.model.request.user.UserDelete;
import com.nguyenmauhuy.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/api/delete")
public class ApiDeleteController extends BaseController {
    private final UserService userService;

    public ApiDeleteController() {
        this.userService = (UserService) BeanFactory.beans.get("userService");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDelete userDelete = convertToModel(req,UserDelete.class);
        userService.deleteUser(userDelete);
    }
}
