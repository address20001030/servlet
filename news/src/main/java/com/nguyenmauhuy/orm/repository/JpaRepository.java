package com.nguyenmauhuy.orm.repository;

import com.nguyenmauhuy.orm.paging.Page;
import com.nguyenmauhuy.orm.paging.PageAble;
import com.nguyenmauhuy.orm.repository.builder.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface JpaRepository <T, ID>{
    void save(T t);

    void update(ID id, T t);

    void delete(ID id);

    void deleteAll(List<ID> ids);

    Optional<T> findById(ID id);

    Stream<T> findAll();

    long count();

    Page<T> findAll(PageAble pageAble);

    Optional<T> find(Query<T> query);

    Page<T> findAll(Query<T> query, PageAble pageAble);

    long count(Query<T> query);
}
