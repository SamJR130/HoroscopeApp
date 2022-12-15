package com.hfad.horoscopeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HoroscopeAdapter extends RecyclerView.Adapter<HoroscopeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserInfo> allPeople;
    private ImageView imvDelete;
    private ImageView imvSign;
    private ImageView imvUserSign;
    private View itemView;

    private FragmentManager fragmentManager;

    public HoroscopeAdapter(FragmentManager fm, ArrayList<UserInfo> n) {
        fragmentManager = fm;
        allPeople = n;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_person_row, parent, false);
        return new MyViewHolder(itemView);
    }


    //BINDS data to an empty row view
    //Position is the index in the list you want to show
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserInfo p = allPeople.get(position);
        holder.setData(p, position);

        //System.out.println("DONE CREATING POPULATING A ROW: " + position + " " + p.getTitleNote());
    }

    @Override
    public int getItemCount() {

        return allPeople.size();
    }

    public void setUsers(ArrayList<UserInfo> users) {
        allPeople = users;
        System.out.println("Error");


        notifyItemRangeChanged(0, allPeople.size());
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int currentPositionInList = -1;
        private UserInfo currentPerson = null;
        private ImageView imvDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            //Create dbHelper

            view.setClickable(true);
            view.setOnClickListener(this);
            imvDelete = itemView.findViewById(R.id.imvDelete);


            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (currentPerson == null)
                    {
                        System.out.println("CURRENT PERSON NULL");
                    }
                    else
                    {
                        System.out.println("CURRENT PERSON NOT NULL" + currentPerson);
                    }
                    System.out.println("SIZE:" + allPeople.size());
                    DBHelper dbHelper = new DBHelper(view.getContext());
                    dbHelper.deleteUser(currentPerson);

                    ArrayList<UserInfo> tempList = dbHelper.fetchAllUsers();
                    System.out.println("UPDATED SIZE:" + tempList.size());

                    System.out.println("HERE " + currentPerson);
                    if(allPeople.size() > 1) {
                        allPeople.remove(currentPositionInList);
                        notifyItemRemoved(currentPositionInList);
                        notifyItemRangeChanged(currentPositionInList, allPeople.size());
                    }
                }
            });
        }


        public void setData(UserInfo p, int pos) {

            int sign;
            currentPositionInList = pos;
            currentPerson = p;
            System.out.println("THIS IS P" + p);
            TextView name = itemView.findViewById(R.id.tvName);
            name.setText(p.getName());
            TextView dob = itemView.findViewById(R.id.tvBirthday);
            dob.setText(getFormattedDate(p.getBirthday()) + "" );
            imvSign = itemView.findViewById(R.id.iv_sign_row);

            String formatDate = getFormattedDate(p.getBirthday());
            //imvSign.setImageResource(getImage(formatDate, imvSign);

            //sign = getImage(getFormattedDate(p.getBirthday()));
            System.out.println("I WORK UP TO HERE");
            long bday = p.getBirthday();
            String formBday = getFormattedDate(bday);
            sign = getImage(formBday);
           imvSign.setImageResource(sign);



           //imvUserSign.setImageResource(sign);
        }


        @Override
        public void onClick(View view) {
            DialogIndividInfo dialog = new DialogIndividInfo(currentPerson,HoroscopeAdapter.this);
            //FragmentActivity fa = (FragmentActivity) view.getContext();
            //FragmentManager fm = context.getSupportFragmentManager();
            //FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
            dialog.show(fragmentManager, "");
        }
    }
    private String getFormattedDate(long dobInMilis){

        SimpleDateFormat formatter = new  SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        Date dobDate =   new Date(dobInMilis);

        String s = formatter.format(dobDate);
        return s;
    }
    public int getImage(String b)
    {
        int image = 0;
        String[] date = b.split("/");
        int d = Integer.parseInt(date[0]);
        String m = date[1];

        //ImageView image = itemView.findViewById(R.id.iv_sign);

        if (m.equals("Jan"))
        {
            if (d >= 1 && d < 20)
            {
                //capricorn
                image = R.drawable.capricorn;
            }
            else
            {
                //aquarius
                image = R.drawable.aquarius;
            }
        }
        else if (m.equals("Feb"))
        {
            if (d >= 1 && d < 19)
            {
                //aquarius
                image = R.drawable.aquarius;
            }
            else
            {
                //pisces
                image = R.drawable.pisces;
            }
        }
        else if (m.equals("Mar"))
        {
            if (d >= 1 && d < 21)
            {
                //pisces
                image = R.drawable.pisces;
            }
            else
            {
                //aries
                image = R.drawable.aries;
            }
        }
        else if (m.equals("Apr"))
        {
            if (d >= 1 && d < 20)
            {
                //aries
                image = R.drawable.aries;
            }
            else
            {
                //taurus
                image = R.drawable.taurus;
            }
        }
        else if (m.equals("May"))
        {
            if (d >= 1 && d < 21)
            {
                //taurus
                image = R.drawable.taurus;
            }
            else
            {
                //gemini
                image = R.drawable.gemini;
            }
        }
        else if (m.equals("Jun"))
        {
            if (d >= 1 && d < 21)
            {
                //gemini
                image = R.drawable.gemini;
            }
            else
            {
                //cancer]
                image = R.drawable.cancer;
            }
        }
        else if (m.equals("Jul"))
        {
            if (d >= 1 && d < 23)
            {
                //cancer
                image = R.drawable.cancer;
            }
            else
            {
                //leo
                image = R.drawable.leo;
            }
        }
        else if (m.equals("Aug"))
        {
            if (d >= 1 && d < 23)
            {
                //leo
                image = R.drawable.leo;
            }
            else
            {
                //virgo
                image = R.drawable.virgo;
            }
        }
        else if (m.equals("Sep"))
        {
            if (d >= 1 && d < 23)
            {
                //virgo
                image = R.drawable.virgo;
            }
            else
            {
                //libra
                image = R.drawable.libra;
            }
        }
        else if (m.equals("Oct"))
        {
            if (d >= 1 && d < 23)
            {
                //libra
                image = R.drawable.libra;
            }
            else
            {
                //scorpio
                image = R.drawable.scorpio;
            }
        }
        else if (m.equals("Nov"))
        {
            if (d >= 1 && d < 22)
            {
                //scorpio
                image = R.drawable.scorpio;
            }
            else
            {
                //sagittarius
                image = R.drawable.sagittarius;
            }
        }
        else if (m.equals("Dec"))
        {
            if (d >= 1 && d < 20)
            {
                //sagittarius

                image = R.drawable.sagittarius;
            }
            else
            {
                //capricorn
                image = R.drawable.capricorn;
            }
        }
        System.out.println("image " + image);
        return image;
    }
}
/*  @Override
            public void onClick(View view) {
                //DialogueShowNote dialog = new DialogueShowNote(currentNote);
                //dialog.show(fragmentManager, "");
                System.out.println("I am the button");
                DialogDisplayInfo dialog = new DialogDisplayInfo(currentPerson);
                FragmentActivity fa = (FragmentActivity) view.getContext();
                FragmentManager fm = fa.getSupportFragmentManager();
                dialog.show(fm, "");
                //DialogNewPersonToHoroList dialog = new DialogNewPersonToHoroList();
                //FragmentActivity fa = (FragmentActivity) context;

            }

 */


