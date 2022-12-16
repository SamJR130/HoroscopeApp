package com.hfad.horoscopeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//Angel And Sam
//This is another fragment that holds a cardview to display the users info
public class DisplayHoroscopeSign extends Fragment {

    private UserInfo user;
    private DBHelper dbHelper;
    private HoroscopeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout_person, container, false);

        setUpRecyclerView(view);

        FloatingActionButton btnAdd = view.findViewById(R.id.fab);

        //The add new user dialog
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //DialogDisplayInfo dialog = new DialogDisplayInfo(user);
                // dialog.show(getParentFragmentManager(), "");
                DialogNewPersonToHoroList dialog = new DialogNewPersonToHoroList(adapter);
                dialog.show(getParentFragmentManager(), "");
            }
        });


        return view;
    }

    //Recycler view
    private void setUpRecyclerView(View view) {
        dbHelper = new DBHelper(getContext());

        ArrayList<UserInfo> userInfos = dbHelper.fetchAllUsers();

        RecyclerView rv = view.findViewById(R.id.recyclerView);
        adapter = new HoroscopeAdapter(getParentFragmentManager(), userInfos);
        rv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);

    }

}