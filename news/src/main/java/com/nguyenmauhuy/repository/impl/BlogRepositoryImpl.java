package com.nguyenmauhuy.repository.impl;

import com.nguyenmauhuy.bean.annotation.Repository;
import com.nguyenmauhuy.entity.Blog;
import com.nguyenmauhuy.orm.repository.impl.AbstractQuerySimple;
import com.nguyenmauhuy.repository.BlogRepository;
@Repository
public class BlogRepositoryImpl extends AbstractQuerySimple<Blog,Long> implements BlogRepository{
}
