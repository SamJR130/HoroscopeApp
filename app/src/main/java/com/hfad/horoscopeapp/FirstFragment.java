package com.hfad.horoscopeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FirstFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        Button start = view.findViewById(R.id.btnStart);
        TextView tvFName = view.findViewById(R.id.tvFirstName);
        TextView tvFBirthday = view.findViewById(R.id.tvFirstBirthday);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = tvFName.getText().toString();
                String birthday = tvFBirthday.getText().toString();



                FirstFragmentDirections.ActionFirstFragmentToLayoutPersonFragment action =
                        FirstFragmentDirections.actionFirstFragmentToLayoutPersonFragment(name, birthday);


                Navigation.findNavController(view).navigate(action);
            }
        });


        return view;
    }



}