package com.nguyenmauhuy.controller.api;

import com.nguyenmauhuy.bean.BeanFactory;
import com.nguyenmauhuy.controller.BaseController;
import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.model.request.user.UserDelete;
import com.nguyenmauhuy.model.request.user.UserSaveRequest;
import com.nguyenmauhuy.model.request.user.UserUpdateRequest;
import com.nguyenmauhuy.orm.paging.PageRequest;
import com.nguyenmauhuy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class ApiUserController extends BaseController {
    private final UserService userService;

    public ApiUserController() {
        this.userService = (UserService) BeanFactory.beans.get("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pageRequest = PageRequest.of(1, 5);
        var page = userService.findAll(pageRequest);
        responseClinet(resp,page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSaveRequest userSaveRequest = convertToModel(req,UserSaveRequest.class);
        userService.save(userSaveRequest);
        responseClinet(resp,userSaveRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserUpdateRequest updateRequest = convertToModel(req,UserUpdateRequest.class);
        userService.update(updateRequest);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDelete userDelete = convertToModel(req,UserDelete.class);
        userService.deleteUser(userDelete);
    }

}
