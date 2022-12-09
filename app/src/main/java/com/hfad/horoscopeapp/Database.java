package com.hfad.horoscopeapp;

import java.util.ArrayList;

public class Database {

    private static ArrayList<PersonInfo> allPeople;

    public static ArrayList<PersonInfo> getAllPeople()
    {
        if (allPeople == null)
        {
            loadPersons();
        }

        return allPeople;
    }

    private static void loadPersons()
    {
        allPeople = new ArrayList<PersonInfo>();
        allPeople.add(new PersonInfo("11/17/1999", "Angel Negron", true));
        allPeople.add(new PersonInfo("11/17/1999", "Angel Negron2", true));
        allPeople.add(new PersonInfo("11/17/1999", "Angel Negron3", true));
        allPeople.add(new PersonInfo("11/17/1999", "Angel Negron4", true));
    }

    public static void addPerson(String name,String bday,boolean test)
    {

        if (getAllPeople() == null)
        {
            loadPersons();
        }
        allPeople.add(new PersonInfo(bday, name, test));
    }


}
