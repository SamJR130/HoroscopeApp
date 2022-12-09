package com.hfad.horoscopeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DisplayHoroscopeSign extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout_person, container, false);


        setUpRecyclerView(view);

        FloatingActionButton btnAdd = view.findViewById(R.id.fab);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogNewPersonToHoroList dialog = new DialogNewPersonToHoroList();
                dialog.show(getParentFragmentManager(), "");
            }
        });



        return view;
    }

    private void setUpRecyclerView(View view)
    {
        RecyclerView rv =  view.findViewById(R.id.recyclerView);
       //Adapter adapter = new Adapter(getSupportFragmentManager(), Database.getNotes());
        HoroscopeAdapter adapter = new HoroscopeAdapter(this.getContext(), Database.getAllPeople());
        rv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);

    }

}