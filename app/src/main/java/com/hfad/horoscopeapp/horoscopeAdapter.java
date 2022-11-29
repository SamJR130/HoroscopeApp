package com.hfad.horoscopeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class horoscopeAdapter extends RecyclerView.Adapter<horoscopeAdapter.MyViewHolder> {

        private ArrayList<Person> allPeople;
        private Context context;
        //private FragmentManager fragmentManager;

        public horoscopeAdapter(Context c, ArrayList<Person>n)
        {
            context = c;
            allPeople = n;
        }

        //Creates an empty view of a single row - inflate a vacation row item
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_person_row, parent, false);

            System.out.println("DONE CREATING A SINGLE ROWS VIEW");
            return new MyViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return allPeople.size();
        }

        //BINDS data to an empty row view
        //Position is the index in the list you want to show
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            Person p = allPeople.get(position);
            holder.setData(p, position);

            //System.out.println("DONE CREATING POPULATING A ROW: " + position + " " + p.getTitleNote());
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {

            private TextView tvPerson;
            private TextView tvBirthday;
            private ImageView imvDelete;

            private int currentPositionInList = -1;
            private Person currentPerson = null;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                tvPerson = itemView.findViewById(R.id.tvName);
                tvBirthday = itemView.findViewById(R.id.tvBirthday);
                imvDelete = itemView.findViewById(R.id.imvDelete);

                imvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        allPeople.remove(currentPositionInList);
                        notifyItemRemoved(currentPositionInList);
                        notifyItemRangeChanged(currentPositionInList, allPeople.size());
                    }
                });

                itemView.setClickable(true);
                itemView.setOnClickListener(this);
            }

            public void setData(Person p, int pos)
            {
                tvPerson.setText(p.getName());
                tvBirthday.setText(p.getBirthday());
                currentPositionInList = pos;
                currentPerson = p;

            }

            @Override
            public void onClick(View view) {
                //DialogueShowNote dialog = new DialogueShowNote(currentNote);
                //dialog.show(fragmentManager, "");
                DialogNewPerson dialog = new DialogNewPerson();
                //FragmentActivity fa = (FragmentActivity) context;
                FragmentActivity fa = (FragmentActivity) view.getContext();
                FragmentManager fm = fa.getSupportFragmentManager();
                dialog.show(fm, "");
            }
        }
    }


