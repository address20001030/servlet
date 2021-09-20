package com.nguyenmauhuy.service;

import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.model.request.user.AuthRequest;
import com.nguyenmauhuy.model.request.user.UserDelete;
import com.nguyenmauhuy.model.request.user.UserSaveRequest;
import com.nguyenmauhuy.model.request.user.UserUpdateRequest;
import com.nguyenmauhuy.orm.paging.Page;
import com.nguyenmauhuy.orm.paging.PageAble;

public interface UserService {
    void save(UserSaveRequest request);

    User auth(AuthRequest authRequest);

    User findById(long id);

    Page<User> findAll(PageAble pageAble);

    void update(UserUpdateRequest updateRequest);

    void deleteUser(UserDelete userDelete);

}
