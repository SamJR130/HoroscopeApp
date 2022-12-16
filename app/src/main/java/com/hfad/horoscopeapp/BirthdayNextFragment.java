package com.hfad.horoscopeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//Angel and Sam
//This class is suppose to show the users in the database
//and whose birthday is the closest
public class BirthdayNextFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View view;
    TextView tvSelectedBday;
    HoroscopeAdapter horoscopeAdapter;
    private DBHelper dbHelper;
    Spinner spName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_birthday_next, container, false);
        dbHelper = new DBHelper(getContext());

        TextView tvCloseName = view.findViewById(R.id.tv_closest_name);
        TextView tvCloseBday = view.findViewById(R.id.tv_closest_bday);
        TextView tvHowClose = view.findViewById(R.id.tv_how_close);

        spName = view.findViewById(R.id.sp_name);

        TextView tvSelectedBdayClose = view.findViewById(R.id.tv_when);

        tvSelectedBday = view.findViewById(R.id.tv_bday);


        ArrayList<String> items = new ArrayList<>();


        for (int i = 0; i < dbHelper.fetchAllUsers().size(); i++) {
            System.out.println("USER NAME" + dbHelper.fetchAllUsers().get(i).getName());
            items.add(dbHelper.fetchAllUsers().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spName.setAdapter(adapter);

        spName.setOnItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();

        //todays date
        tvSelectedBdayClose.setText(getFormattedDate(calendar.getTimeInMillis()));

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        tvSelectedBday.setText(dbHelper.getBday(spName.getSelectedItem().toString()));
        System.out.println("Item selected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //Format the date
    private String getFormattedDate(long dobInMilis) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        Date dobDate = new Date(dobInMilis);

        String s = formatter.format(dobDate);
        return s;
    }

    /*
    public void findClosestBday() {
        try {
            DateFormat df = new SimpleDateFormat("hh:mm:ss_yyyy.MM.dd");
            Date date1 = new java.util.Date();
            Date date2 = df.parse("00:00:00_2013.01.01");
            long diff = date2.getTime() - date1.getTime();
            System.out.println("TEST" + date1.getTime() + " - " + date2.getTime() + " - " + diff / 1000 / 60 / 60 / 24);
        } catch (
                ParseException e) {
            System.out.println("TEST" + "Exception" + e);
        }
    }
    */

}