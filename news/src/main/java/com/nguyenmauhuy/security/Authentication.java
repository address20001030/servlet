package com.nguyenmauhuy.security;

import com.nguyenmauhuy.model.request.user.AuthRequest;

import javax.servlet.http.HttpServletRequest;

public interface Authentication {

    String authenticate(AuthRequest authRequest, HttpServletRequest then);
}
