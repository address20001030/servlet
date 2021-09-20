package com.nguyenmauhuy.repository.impl;

import com.nguyenmauhuy.bean.annotation.Repository;
import com.nguyenmauhuy.entity.Category;
import com.nguyenmauhuy.orm.repository.impl.AbstractQuerySimple;
import com.nguyenmauhuy.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl extends AbstractQuerySimple<Category,Long> implements CategoryRepository {
}
