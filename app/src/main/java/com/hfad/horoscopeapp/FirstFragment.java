package com.hfad.horoscopeapp;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.*;

public class FirstFragment extends Fragment {

    private Calendar myCalendar = Calendar.getInstance();
    private HoroscopeAdapter horoAdapter;
    private DBHelper dbHelper;
    private Button start;
    private EditText tvFName;
    private EditText tvFBirthday;
    private String monthPerson;
    private int datePerson;

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("Here " + dbHelper.fetchAllUsers().size());
        if (dbHelper.fetchAllUsers().size() > 0)
            Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_layoutPersonFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = inflater.inflate(R.layout.fragment_first, null);

        start = view.findViewById(R.id.btnStart);
        tvFName = view.findViewById(R.id.tvFirstName);
        tvFBirthday = view.findViewById(R.id.tvFirstBirthday);

        //Sets the text to the formatted date
        //tvFBirthday.setText(getFormattedDate(user.getBirthday()));


        DatePickerDialog.OnDateSetListener  date =  new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvFBirthday.setText(getFormattedDate(myCalendar.getTimeInMillis()));
                //System.out.println("THIS IS FORMAT DATE" + getFormattedDate(myCalendar.getTimeInMillis()));
            }
        };

        Activity activity = getActivity();
        tvFBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //myCalendar.setTimeInMillis(employeeToShow.getDob());
                DatePickerDialog dialog = new DatePickerDialog(activity,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                monthPerson =  String.valueOf(myCalendar.get(Calendar.MONTH));
                datePerson = myCalendar.get(Calendar.DAY_OF_MONTH);

                dialog.show();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = tvFName.getText().toString();

                String birthday = tvFBirthday.getText().toString();
                if(name.equals(""))
                {
                    String toastString = "Fill out all fields";
                    Toast toast = new Toast(getContext());
                    toast.makeText(getContext(), toastString, Toast.LENGTH_SHORT).show();
                    System.out.println("IT made it");
                }
                else {
                    saveUserInfo();
                    Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_layoutPersonFragment);
                }
            }
        });


        //Create dbHelper
        dbHelper = new DBHelper(getContext());

        return view;
    }


    private void saveUserInfo()
    {
        String toastString = null;
        String name = tvFName.getText().toString();
        long callInMS = myCalendar.getTimeInMillis();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(callInMS));

            dbHelper.saveUser(name, callInMS);
            ArrayList<UserInfo> users = new ArrayList<UserInfo>();
                    users = dbHelper.fetchAllUsers();

            toastString = "User added";


//        Toast t = Toast.makeText(getContext(),toastString,Toast.LENGTH_LONG);
//        t.show();
    }


    private String getFormattedDate(long dobInMilis){

        SimpleDateFormat formatter = new  SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        Date dobDate =   new Date(dobInMilis);

        String s = formatter.format(dobDate);
        return s;
    }


}