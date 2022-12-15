
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

public class MainActivity extends AppCompatActivity {

    //View view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.horoscope_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        View view = findViewById(R.id.nav_host_fragment);
        switch(item.getItemId()){
            case R.id.home:

                Navigation.findNavController(view).navigate(R.id.action_birthdayNextFragment_to_layoutPersonFragment);
                Toast.makeText(this, "Home selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.other:

                Navigation.findNavController(view).navigate(R.id.action_layoutPersonFragment_to_birthdayNextFragment);
                Toast.makeText(this, "Birthday is selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item_icon:
                System.out.println("Menu option");
                //openBrowser(view);

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void openBrowser(View view){

        //Get url from tag
       String url = (String)view.findViewById(R.id.item_icon).getTag();

        System.out.println("URL" + url);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

}
