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

        System.out.println("DONE CREATING A SINGLE ROWS VIEW");
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        setUpRecyclerView();

        FloatingActionButton btnAdd = view.findViewById(R.id.fab);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogNewPerson dialog = new DialogNewPerson();
                dialog.show(getParentFragmentManager(), "");
            }
        });


        Button start = view.findViewById(R.id.btnStart);
        TextView tvFName = view.findViewById(R.id.tvFirstName);
        TextView tvFBirthday = view.findViewById(R.id.tvFirstBirthday);

        //FloatingActionButton btnAdd = view.findViewById(R.id.fab);

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


    private void setUpRecyclerView()
    {
        RecyclerView rv =   (RecyclerView) getView().findViewById(R.id.recyclerView);
/*
        //adapter
        //Adapter adapter = new Adapter(getSupportFragmentManager(), Database.getNotes());
        horoscopeAdapter adapter = new horoscopeAdapter(this, Database.getAllPeople());
        rv.setAdapter(adapter);

        //manager connects

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);*/
    }
}