
package com.hfad.horoscopeapp;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Sam and Angel
//This class holds the menu
public class MainActivity extends AppCompatActivity {
    String temp;

    //View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //inflates the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.horoscope_menu, menu);

        return true;
    }


    /**
     * This method creates an action when an item is selected from the menu
     * It can send the user between two screens
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        View view = findViewById(R.id.nav_host_fragment);

        switch (item.getItemId()) {
            case R.id.home:
                //Sends the user to the home screen which is the cardview of users
                Navigation.findNavController(view).navigate(R.id.action_birthdayNextFragment_to_layoutPersonFragment);
                Toast.makeText(this, "Home selected", Toast.LENGTH_LONG).show();
                return true;

            case R.id.other:
                //Sends the user to the Screen of Users Birthdays
                Navigation.findNavController(view).navigate(R.id.action_layoutPersonFragment_to_birthdayNextFragment);
                Toast.makeText(this, "Birthday is selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item_icon:
                //Sends the user to a website link for zodiac signs
                //A uri helps deal with URLS
                Uri uri = Uri.parse("https://www.zodiacsign.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


