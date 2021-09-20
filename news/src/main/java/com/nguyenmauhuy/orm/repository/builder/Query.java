package com.nguyenmauhuy.orm.repository.builder;

import java.util.List;

public interface Query <T>{
    String condition();

    Object value();

    List<Object> values();

    static <T> Query<T> query(String attributeName, Expression expression, Object value){
        return new QueryImpl<>(attributeName, expression ,value);
    }
}
