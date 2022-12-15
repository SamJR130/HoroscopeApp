package com.hfad.horoscopeapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DialogIndividInfo extends DialogFragment {
    private UserInfo pi;
    private TextView tvName;
    private TextView birthday;
    private TextView description;
    private ImageView sign;
    private HoroscopeAdapter horoAdapter;

    public DialogIndividInfo(UserInfo p,HoroscopeAdapter a)
    {
        pi = p;
        horoAdapter = a;
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_display_row, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        tvName = dialogView.findViewById(R.id.tv_name);
        birthday = dialogView.findViewById(R.id.tv_birthday);
        description = dialogView.findViewById(R.id.tv_description);
        sign = dialogView.findViewById(R.id.iv_sign_dialogue);
        tvName.setText(pi.getName());
        birthday.setText(getFormattedDate(pi.getBirthday()));
        builder.setView(dialogView);
        //set image
        long bday = pi.getBirthday();
        String formBday = getFormattedDate(bday);
        sign.setImageResource(horoAdapter.getImage(formBday));
        return builder.create();
    }

    String getSignDescription(String birthday)
    {
      String d = "";
        return d;
    }

    private  String  getFormattedDate(long dobInMilis){

        SimpleDateFormat formatter = new  SimpleDateFormat("dd/MMM/yyyy",
                Locale.getDefault());

        Date dobDate =   new Date(dobInMilis);

        String s = formatter.format(dobDate);

        return s;
    }



}
