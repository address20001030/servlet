package com.nguyenmauhuy.orm.repository.builder;

public interface Specification <T>{
    Specification<T> toPredicate();
}
