package com.hfad.horoscopeapp;

public class PersonInfo {
    private String birthday;



    private String name;
    private boolean isAppOwner;

    public PersonInfo(String birthday, String name, boolean isAppOwner) {
        this.birthday = birthday;
        this.name = name;
        this.isAppOwner = isAppOwner;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAppOwner() {
        return isAppOwner;
    }

    public void setAppOwner(boolean appOwner) {
        isAppOwner = appOwner;
    }


    @Override
    public String toString() {
        return "Person{" +
                "birthday='" + birthday + '\'' +
                ", name='" + name + '\'' +
                ", isAppOwner=" + isAppOwner +
                '}';
    }
}
