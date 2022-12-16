package com.hfad.horoscopeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


//Sam and Angel
//This class adds a new user to the dtabase via a dialog that requires input
public class DialogNewPersonToHoroList extends DialogFragment {
    private Calendar myCalendar = Calendar.getInstance();
    private UserInfo personNow;
    private HoroscopeAdapter horoAdapter;
    private EditText tvNewPerson;
    private EditText tvNewBirthday;
    private Button btnCancel;
    private Button btnOk;
    private DBHelper dbHelper;

    public DialogNewPersonToHoroList(HoroscopeAdapter a) {
        horoAdapter = a;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_person, null);

        tvNewPerson = dialogView.findViewById(R.id.tvNewPerson);
        tvNewBirthday = dialogView.findViewById(R.id.tvNewBirthday);
        btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnOk = dialogView.findViewById(R.id.btnOk);

        //Set calender date
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvNewBirthday.setText(getFormattedDate(myCalendar.getTimeInMillis()));
            }

        };


        //Calander pops up when clicked
        Activity activity = getActivity();
        tvNewBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //myCalendar.setTimeInMillis(personNow.getBirthday());
                DatePickerDialog dialog = new DatePickerDialog(activity,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //Exit dialog
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //Save the new user to database
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveUser();
                dismiss();
            }
        });

        //Create dbHelper
        dbHelper = new DBHelper(getContext());

        builder.setView(dialogView);
        return builder.create();
    }

    //Saved User using adapter and dbhelper
    private void saveUser() {
        String toastString = null;
        String name = tvNewPerson.getText().toString();
        long callInMS = myCalendar.getTimeInMillis();

        //Checks name is not empty before adding to databse
        if (name.equals("")) {
            toastString = "Fill out all fields";
            Toast.makeText(getContext(), toastString, Toast.LENGTH_SHORT);
        } else {
            dbHelper.saveUser(name, callInMS);
            ArrayList<UserInfo> users = dbHelper.fetchAllUsers();

            //Sets the users
            horoAdapter.setUsers(users);
            horoAdapter.notifyDataSetChanged();
            horoAdapter.notifyItemRangeChanged(0, users.size());

            toastString = "User added";
        }
        Toast t = Toast.makeText(getContext(), toastString, Toast.LENGTH_LONG);
        t.show();
    }

    //Formats the dates
    private String getFormattedDate(long dobInMilis) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy",
                Locale.getDefault());

        Date dobDate = new Date(dobInMilis);

        String s = formatter.format(dobDate);

        return s;
    }
}
