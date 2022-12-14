package com.hfad.horoscopeapp;

import android.provider.BaseColumns;

//Our databse being created
///Angel and Sam
public class DBContract {

    class UserEntry implements BaseColumns {

        //Create table
        public static final String TABLE_NAME = "horoscopes";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DOB = "dob";
        public static final String COLUMN_FORMAT_BDAY = "bdayFormatted";
        public static final String COLUMN_ID = _ID;

        public static final String CREATE_USER_TABLE_CMD = "CREATE TABLE "
                + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_DOB + " INTEGER NOT NULL, "
                + COLUMN_FORMAT_BDAY + " TEXT NOT NULL);";

        public static final String DROP_USER_TABLE_CMD = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }
}
