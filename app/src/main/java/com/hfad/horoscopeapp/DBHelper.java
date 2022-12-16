package com.hfad.horoscopeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//Angel and Sam
//This class lets us execute queries using the database we created
public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("Here" + DBContract.UserEntry.CREATE_USER_TABLE_CMD);

        db.execSQL(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(DBContract.UserEntry.DROP_USER_TABLE_CMD);
        onCreate(db);
    }

    //Save user to databse
    public void saveUser(String name, long dobMS) {
        String b = getFormattedDate(dobMS);
        int image = 0;
        String[] date = b.split("/");
        int d = Integer.parseInt(date[0]);
        String m = date[1];

        String monthDay = m + "/" + d;


        String insert = String.format("INSERT INTO %s ( %s, %s, %s) " + "VALUES('%s', %d, '%s');",
                DBContract.UserEntry.TABLE_NAME,
                DBContract.UserEntry.COLUMN_NAME,
                DBContract.UserEntry.COLUMN_DOB,
                DBContract.UserEntry.COLUMN_FORMAT_BDAY,
                name,
                dobMS,
                monthDay
        );

        //System.out.println(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
        System.out.println("SAVING: " + insert);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insert);

        db.close();

    }

    /*
    Attempted to alter a users info ran into errors

    public void alterUser(String desc)
    {
        String alter = String.format("UPDATE %s SET %s = '%s';",
                DBContract.UserEntry.TABLE_NAME ,
                DBContract.UserEntry.COLUMN_H_SIGN,
                name,
                dobMS
        );

        //System.out.println(DBContract.UserEntry.CREATE_USER_TABLE_CMD);
        System.out.println("SAVING: " + alter);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(alter);

        db.close();

    }
*/

    //Retrieves a users birthday based of the name
    public String getBday(String name) {
        String bday = "";

        String findBday = "SELECT " + DBContract.UserEntry.COLUMN_FORMAT_BDAY + " FROM " + DBContract.UserEntry.TABLE_NAME
                + " WHERE " + DBContract.UserEntry.COLUMN_NAME + " = '" + name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(findBday, null);

        //get the positions of your columns
        int formPos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_FORMAT_BDAY);

        while (cursor.moveToNext()) {
            //Gets info from current record
            String form = cursor.getString(formPos);
            bday = form;

            //  selectedUser.setBirthday(form);
        }

        cursor.close();
        db.close();
        return bday;
    }


    //Gets all the users in databse
    public ArrayList<UserInfo> fetchAllUsers() {
        ArrayList<UserInfo> allUsers = new ArrayList<>();
        String selectAllString = "SELECT * FROM " + DBContract.UserEntry.TABLE_NAME;

        System.out.println("SAVING " + selectAllString);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectAllString, null);

        //get the positions of your columns
        int idPos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID);
        int namePos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME);
        int dobPos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DOB);
        int formPos = cursor.getColumnIndex(DBContract.UserEntry.COLUMN_FORMAT_BDAY);

        while (cursor.moveToNext()) {
            //Gets info from current record
            long id = cursor.getLong(idPos);
            long dob = cursor.getLong(dobPos);
            String name = cursor.getString(namePos);

            //long birthday, String name, boolean isAppOwner

            allUsers.add(new UserInfo(dob, name, id));
        }

        cursor.close();
        db.close();
        return allUsers;

    }

    //deletes a selected user from database
    public void deleteUser(UserInfo user) {
        //IS DATABASE NULL?
        if (fetchAllUsers().size() > 1) {
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


    //Updates a user
    public void updateUser(UserInfo user) {
        String updateString = String.format("UPDATE %s SET %s = '%s', " +
                        " %s = '%s'," +
                        " %s = %d" +
                        " WHERE %s = %d;",
                DBContract.UserEntry.TABLE_NAME,
                DBContract.UserEntry.COLUMN_NAME,
                user.getName(),
                DBContract.UserEntry.COLUMN_DOB,
                user.getBirthday());

        System.out.println("Updating " + updateString);

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(updateString);
        db.close();

    }

    //formatted date
    private String getFormattedDate(long dobInMilis) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        Date dobDate = new Date(dobInMilis);

        String s = formatter.format(dobDate);
        return s;
    }


}