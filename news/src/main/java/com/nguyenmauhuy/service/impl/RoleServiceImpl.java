package com.nguyenmauhuy.service.impl;

import com.nguyenmauhuy.bean.annotation.Autowire;
import com.nguyenmauhuy.bean.annotation.Service;
import com.nguyenmauhuy.entity.Role;
import com.nguyenmauhuy.repository.RoleRepository;
import com.nguyenmauhuy.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowire
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll().collect(Collectors.toList());
    }
}
