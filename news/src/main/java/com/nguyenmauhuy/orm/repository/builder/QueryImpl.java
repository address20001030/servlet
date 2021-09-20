package com.nguyenmauhuy.orm.repository.builder;

import java.util.List;

public class QueryImpl<T> implements Query<T> {
    private String condition;
    private Object value;
    private List<Object> values;

    public QueryImpl(String condition, Expression expression, Object value) {
        this.condition = new StringBuilder(condition).append(expression.expression()).append("?").toString();
        this.value = value;
    }

    public QueryImpl(String condition, Expression expression) {
        this.condition = new StringBuilder(condition).append(expression.expression()).toString();
    }

    public QueryImpl(String condition, List<Object> values) {
        this.condition = condition;
        this.values = values;

    }

    public QueryImpl(String condition, Object value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public String condition() {
        return condition;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public List<Object> values() {
        return this.values;
    }
}
