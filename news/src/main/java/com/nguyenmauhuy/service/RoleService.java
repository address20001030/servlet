package com.nguyenmauhuy.service;

import com.nguyenmauhuy.entity.Role;

import java.util.List;

public interface RoleService {
    Role findById(long id);

    List<Role> getAll();
}
