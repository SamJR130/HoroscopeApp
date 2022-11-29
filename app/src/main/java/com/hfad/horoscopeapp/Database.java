package com.hfad.horoscopeapp;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Person> allPeople;

    public static ArrayList<Person> getAllPeople()
    {

        return allPeople;
    }

    public static void addPerson(String birthday, String name, boolean main)
    {
        allPeople.add(new Person(birthday, name, main));
    }

}
