package com.nguyenmauhuy.controller;

import com.nguyenmauhuy.bean.BeanFactory;
import com.nguyenmauhuy.entity.Role;
import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.exception.ObjectNotFoundException;
import com.nguyenmauhuy.service.RoleService;
import com.nguyenmauhuy.service.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/form/user")
public class UserController extends AdminController {

    private UserService userService;
    private RoleService roleService;

    private static final String ACTION = "update";
    private static final String ACTIONS = "delete";

    public UserController() {
        this.userService = (UserService) BeanFactory.beans.get("userService");
        this.roleService = (RoleService) BeanFactory.beans.get("roleService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("/views/UserInsertOrUpdate.jsp");
        String action = req.getParameter("action");
        long id = StringUtils.isEmpty(req.getParameter("id")) ? 0 : Integer.parseInt(req.getParameter("id"));
        if (ACTION.equals(action)) {
            try {
                User user = userService.findById(id);
                req.setAttribute("user", user);
                List<Role> roles = roleService.getAll();
                req.setAttribute("roles", roles);
            } catch (ObjectNotFoundException e) {
                resp.sendRedirect("/form/404");
            }
        }
        rs.forward(req, resp);
    }
}
