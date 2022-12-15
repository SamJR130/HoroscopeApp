package com.hfad.horoscopeapp;

import android.provider.BaseColumns;

public class DBContract {

    class UserEntry implements BaseColumns
    {

        public static final String TABLE_NAME = "horoscopes";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DOB = "dob";
        public static final String COLUMN_H_SIGN = "horoscopeSign";
        public static final String COLUMN_ID = _ID;

        public static final String CREATE_USER_TABLE_CMD = "CREATE TABLE "
                + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_DOB + " INTEGER NOT NULL);";

        public static final String DROP_USER_TABLE_CMD = "DROP TABLE IF EXISTS " + TABLE_NAME;





    }
}