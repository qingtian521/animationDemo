package com.huaan.animationdemo.javabean;

import java.util.List;

/**
 * 人员信息实体类
 */
public class PersonBean {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private int age;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 距离
     */
    private String distance;

    /**
     * 背景墙列表
     */

    private String[] imglists;


    public PersonBean() {
    }

    /**
     *
     * @param nickname 昵称
     * @param age 年龄
     * @param constellation 星座
     * @param distance 距离
     * @param imglists 背景墙列表
     */
    public PersonBean(String nickname, int age, String constellation, String distance, String[] imglists) {
        this.nickname = nickname;
        this.age = age;
        this.constellation = constellation;
        this.distance = distance;
        this.imglists = imglists;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String[] getImglists() {
        return imglists;
    }

    public void setImglists(String[] imglists) {
        this.imglists = imglists;
    }
}

