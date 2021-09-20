package com.nguyenmauhuy.entity;

import com.nguyenmauhuy.orm.annotation.Column;
import com.nguyenmauhuy.orm.annotation.Entity;
import com.nguyenmauhuy.orm.annotation.Id;

@Entity
public class Category {
    @Id
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
