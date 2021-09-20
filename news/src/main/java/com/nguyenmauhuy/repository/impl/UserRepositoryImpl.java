package com.nguyenmauhuy.repository.impl;

import com.nguyenmauhuy.bean.annotation.Repository;
import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.orm.repository.impl.AbstractQuerySimple;
import com.nguyenmauhuy.repository.UserRepository;

@Repository
public class UserRepositoryImpl extends AbstractQuerySimple<User, Long> implements UserRepository {
}
