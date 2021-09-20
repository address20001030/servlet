package com.nguyenmauhuy.repository.impl;

import com.nguyenmauhuy.bean.annotation.Repository;
import com.nguyenmauhuy.entity.Role;
import com.nguyenmauhuy.orm.repository.impl.AbstractQuerySimple;
import com.nguyenmauhuy.repository.RoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.nguyenmauhuy.util.ReflectionUtil.convertToEntity;


@Repository
public class RoleRepositoryImpl extends AbstractQuerySimple<Role,Long> implements RoleRepository {

}
