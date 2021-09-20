package com.nguyenmauhuy.controller.api;


import com.nguyenmauhuy.bean.BeanFactory;
import com.nguyenmauhuy.controller.BaseController;
import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.model.request.user.AuthRequest;
import com.nguyenmauhuy.model.response.AuthResponse;
import com.nguyenmauhuy.security.Authentication;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/login"})
public class ApiAuthController extends BaseController {

    private final Authentication authentication;

    public ApiAuthController( ) {
        this.authentication = (Authentication) BeanFactory.beans.get("authentication");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthRequest authRequest = convertToModel(req, AuthRequest.class);
        String url = this.authentication.authenticate(authRequest,req);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUrl(url);
        responseClinet(resp, authResponse);
    }
}
