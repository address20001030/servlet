package com.nguyenmauhuy.entity;

import com.nguyenmauhuy.orm.annotation.Column;
import com.nguyenmauhuy.orm.annotation.Entity;
import com.nguyenmauhuy.orm.annotation.Id;

@Entity
public class Blog {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String thumbnail;

    @Column
    private String content;

    @Column
    private String userId;

    @Column
    private long categoryId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
