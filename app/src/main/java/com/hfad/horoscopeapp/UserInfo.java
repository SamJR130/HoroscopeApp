package com.hfad.horoscopeapp;

//This class creates a user and their information
//Sam and Angel
public class UserInfo {
    private long birthday;
    private String name;
    private long id;

    //Constructor
    public UserInfo(long birthday, String name, long id) {
        this.birthday = birthday;
        this.name = name;
        this.id = id;

    }

    //Setters and getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "birthday='" + birthday + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}