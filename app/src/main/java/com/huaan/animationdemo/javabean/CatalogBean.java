package com.huaan.animationdemo.javabean;

/**
 * 首页目录实体类
 */
public class CatalogBean {
    private String title;
    private int code;

    public CatalogBean(String title, int code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
