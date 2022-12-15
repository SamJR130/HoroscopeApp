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

import java.util.ArrayList;


public class BirthdayNextFragment extends Fragment implements AdapterView.OnItemSelectedListener{

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
        //tvCloseName.setText(dbHelper.fetchAllUsers().get());

        //spName.getSelectedItem();


        ArrayList<String> items = new ArrayList<>();


        for (int i = 0; i < dbHelper.fetchAllUsers().size(); i++)
        {
            System.out.println("USER NAME" + dbHelper.fetchAllUsers().get(i).getName());
            items.add(dbHelper.fetchAllUsers().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item,items);
        spName.setAdapter(adapter);

        spName.setOnItemSelectedListener(this);
        //tvSelectedBday.setText(dbHelper.getUser(spName.getSelectedItem().toString()).getBirthday() + "");
       // spName.onClick(View view);
        System.out.println("SPNAME" + spName.getSelectedItem().toString());
        //tvSelectedBday.setText(dbHelper.getBday(spName.getSelectedItem().toString()));
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
}