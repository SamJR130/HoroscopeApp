package com.hfad.horoscopeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {

    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println(DBContract.UserEntry.CREATE_USER_TABLE_CMD);

        db.execSQL(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(DBContract.UserEntry.DROP_USER_TABLE_CMD);
        onCreate(db);
    }

    public void saveUser(String name, long dobMS)
    {
        String insert = String.format("INSERT INTO %s ( %s, %s) " + "VALUES('%s', %d);",
                        DBContract.UserEntry.TABLE_NAME ,
                        DBContract.UserEntry.COLUMN_NAME,
                        DBContract.UserEntry.COLUMN_DOB,
                        name,
                        dobMS
                        );

        //System.out.println(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
        System.out.println("SAVING: " + insert);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insert);

        db.close();

    }

    public void alterUser(String name, long dobMS)
    {
        String alter = String.format("UPDATE %s SET %S" + ";",
                DBContract.UserEntry.TABLE_NAME ,
                DBContract.UserEntry.COLUMN_NAME,
                DBContract.UserEntry.COLUMN_DOB,
                name,
                dobMS
        );

        //System.out.println(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
        System.out.println("SAVING: " + alter);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(alter);

        db.close();

    }

    public ArrayList<UserInfo> fetchAllUsers()
    {
        ArrayList<UserInfo> allUsers = new ArrayList<>();
        String selectAllString = "SELECT * FROM " + DBContract.UserEntry.TABLE_NAME;

        System.out.println("SAVING " + selectAllString);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectAllString,null);

        //get the positions of your columns
        int idPos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID);
        int namePos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME);
        int dobPos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DOB);

        while(cursor.moveToNext())
        {
            //Gets info from current record
            long id = cursor.getLong(idPos);
            long dob = cursor.getLong(dobPos);
            String name = cursor.getString(namePos);

            //long birthday, String name, boolean isAppOwner

            allUsers.add(new UserInfo(dob,name,id));
        }

        cursor.close();
        db.close();
        return allUsers;

    }

    public void deleteUser(UserInfo user)
    {
        //IS DATABASE NULL?
        if(fetchAllUsers().size() > 1) {
            System.out.println("HELLO DATABASE");
            SQLiteDatabase db = this.getWritableDatabase();

            String delete = String.format("DELETE FROM " + DBContract.UserEntry.TABLE_NAME
                    + " WHERE " + DBContract.UserEntry.COLUMN_ID + "=" +
                    user.getId() + ";");

            //System.out.println(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
            System.out.println("DELETING: " + delete);

            db.execSQL(delete);
            db.close();
        }
    }



    public void updateUser(UserInfo user)
    {
        String updateString = String.format("UPDATE %s SET %s = '%s', " +
                        " %s = '%s'," +
                        " %s = %d" +
                        " WHERE %s = %d;",
                DBContract.UserEntry.TABLE_NAME,
                DBContract.UserEntry.COLUMN_NAME,
                user.getName(),
                DBContract.UserEntry.COLUMN_DOB,
                user.getBirthday());

        System.out.println("Updating "+ updateString);

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(updateString);
        db.close();

    }





}