package com.nguyenmauhuy.repository;

import com.nguyenmauhuy.entity.Blog;
import com.nguyenmauhuy.orm.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {
}
