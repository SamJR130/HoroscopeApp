package com.hfad.horoscopeapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNewPersonToHoroList extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_person, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        TextView tvNewPerson = dialogView.findViewById(R.id.tvNewPerson);
        TextView tvNewBirthday = dialogView.findViewById(R.id.tvNewBirthday);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOk);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add to notes;
                String newPerson = tvNewPerson.getText().toString();
                String newBirthday = tvNewBirthday.getText().toString();

                if (Database.getAllPeople().size() == 0)
                {
                    Database.addPerson(newPerson, newBirthday, true);
                }
                else
                {
                    Database.addPerson(newPerson, newBirthday, false);
                }
                dismiss();
            }
        });

        builder.setView(dialogView);
        return builder.create();
    }

}
