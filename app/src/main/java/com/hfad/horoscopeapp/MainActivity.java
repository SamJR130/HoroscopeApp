package com.hfad.horoscopeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();

        FloatingActionButton btnAdd = findViewById(R.id.fab);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogNewPerson dialog = new DialogNewPerson();
                dialog.show(getSupportFragmentManager(), "");
            }
        });
    }

    private void setUpRecyclerView()
    {
        RecyclerView rv = findViewById(R.id.recyclerView);

        //adapter
        //Adapter adapter = new Adapter(getSupportFragmentManager(), Database.getNotes());
        horoscopeAdapter adapter = new horoscopeAdapter(this, Database.getAllPeople());
        rv.setAdapter(adapter);

        //manager connects

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);
    }
}