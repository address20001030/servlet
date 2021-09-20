package com.nguyenmauhuy.security;

import com.nguyenmauhuy.bean.annotation.Autowire;
import com.nguyenmauhuy.bean.annotation.Component;
import com.nguyenmauhuy.entity.Role;
import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.exception.ObjectNotFoundException;
import com.nguyenmauhuy.model.request.user.AuthRequest;
import com.nguyenmauhuy.model.response.AuthResponse;
import com.nguyenmauhuy.service.RoleService;
import com.nguyenmauhuy.service.UserService;
import com.nguyenmauhuy.util.SessionUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationImpl implements Authentication{
    @Autowire
    private final UserService userService;
    @Autowire
    private final RoleService roleService;

    public AuthenticationImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String authenticate(AuthRequest authRequest, HttpServletRequest request) {
        try {
            User user = userService.auth(authRequest);
            SessionUtil.put("USER", user, request);
            String roles = roleService.findById(user.getRole_id()).getName();
            if (roles.equals("ADMIN")){
               return "/form/admin";
            }
            return "/form/home";
        }catch (ObjectNotFoundException e){
            return "/form/login?message=tài khoản hoặc mật khẩu không đúng";
        }
    }
}
