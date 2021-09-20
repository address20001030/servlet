package com.nguyenmauhuy.repository;

import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.orm.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
