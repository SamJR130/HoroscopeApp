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

    //private Context context;
    private ArrayList<UserInfo> allPeople;
    private ImageView imvDelete;
    private ImageView imvSign;
    private ImageView imvUserSign;
    private View itemView;
    public String description;

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
                description = "What is the most valuable resource? For Capricorn, " +
                        "the answer is clear: Time. Capricorn is climbing the mounta" +
                        "in straight to the top and knows that patience, perseveranc" +
                        "e, and dedication is the only way to scale. The last earth s" +
                        "ign of the zodiac, Capricorn, is represented by the sea-goat" +
                        ", a mythological creature with the body of a goat and the ta" +
                        "il of a fish. Accordingly, Capricorns are skilled at navigati" +
                        "ng both the material and emotional realms.";
            }
            else
            {
                //aquarius
                image = R.drawable.aquarius;
                description = "Despite the \"aqua\" in its name, Aquarius is actually " +
                        "the last air sign of the zodiac. Innovative, progressive, and" +
                        " shamelessly revolutionary, Aquarius is represented by the wat" +
                        "er bearer, the mystical healer who bestows water, or life, upo" +
                        "n the land. Accordingly, Aquarius is the most humanitarian astr" +
                        "ological sign. At the end of the day, Aquarius is dedicated to" +
                        " making the world a better place.";
            }
        }
        else if (m.equals("Feb"))
        {
            if (d >= 1 && d < 19)
            {
                //aquarius
                image = R.drawable.aquarius;
                description = "Despite the \"aqua\" in its name, Aquarius is actually " +
                        "the last air sign of the zodiac. Innovative, progressive, and" +
                        " shamelessly revolutionary, Aquarius is represented by the wat" +
                        "er bearer, the mystical healer who bestows water, or life, upo" +
                        "n the land. Accordingly, Aquarius is the most humanitarian astr" +
                        "ological sign. At the end of the day, Aquarius is dedicated to" +
                        " making the world a better place.";
            }
            else
            {
                //pisces
                image = R.drawable.pisces;
                description = "If you looked up the word \"psychic\" in the dictionary, " +
                        "there would definitely be a picture of Pisces next to it. Pis" +
                        "ces is the most intuitive, sensitive, and empathetic sign of " +
                        "the entire zodiac — and that’s because it’s the last of the l" +
                        "ast. As the final sign, Pisces has absorbed every lesson — th" +
                        "e joys and the pain, the hopes and the fears — learned by all" +
                        " of the other signs. It's symbolized by two fish swimming in " +
                        "opposite directions, representing the constant division of Pi" +
                        "sces' attention between fantasy and reality.";
            }
        }
        else if (m.equals("Mar"))
        {
            if (d >= 1 && d < 21)
            {
                //pisces
                image = R.drawable.pisces;
                description = "If you looked up the word \"psychic\" in the dictionary, " +
                        "there would definitely be a picture of Pisces next to it. Pis" +
                        "ces is the most intuitive, sensitive, and empathetic sign of " +
                        "the entire zodiac — and that’s because it’s the last of the l" +
                        "ast. As the final sign, Pisces has absorbed every lesson — th" +
                        "e joys and the pain, the hopes and the fears — learned by all" +
                        " of the other signs. It's symbolized by two fish swimming in " +
                        "opposite directions, representing the constant division of Pi" +
                        "sces' attention between fantasy and reality.";
            }
            else
            {
                //aries
                image = R.drawable.aries;
                description = "The first sign of the zodiac, Aries loves to be number one." +
                        " Naturally, this dynamic fire sign is no stranger to competition." +
                        " Bold and ambitious, Aries dives headfirst into even the most cha" +
                        "llenging situations—and they'll make sure they always come out on" +
                        " top!";
            }
        }
        else if (m.equals("Apr"))
        {
            if (d >= 1 && d < 20)
            {
                //aries
                image = R.drawable.aries;
                description = "The first sign of the zodiac, Aries loves to be number one." +
                        " Naturally, this dynamic fire sign is no stranger to competition." +
                        " Bold and ambitious, Aries dives headfirst into even the most cha" +
                        "llenging situations—and they'll make sure they always come out on" +
                        " top!";
            }
            else
            {
                //taurus
                image = R.drawable.taurus;
                description = "What sign is more likely to take a six-hour bath, followed" +
                        " by a luxurious Swedish massage and decadent dessert spread? Why" +
                        " Taurus, of course! Taurus is an earth sign represented by the bu" +
                        "ll. Like their celestial spirit animal, Taureans enjoy relaxing i" +
                        "n serene, bucolic environments surrounded by soft sounds, soothi" +
                        "ng aromas, and succulent flavors.";
            }
        }
        else if (m.equals("May"))
        {
            if (d >= 1 && d < 21)
            {
                //taurus
                image = R.drawable.taurus;
                description = "What sign is more likely to take a six-hour bath, followed" +
                        " by a luxurious Swedish massage and decadent dessert spread? Why" +
                        " Taurus, of course! Taurus is an earth sign represented by the bu" +
                        "ll. Like their celestial spirit animal, Taureans enjoy relaxing i" +
                        "n serene, bucolic environments surrounded by soft sounds, soothi" +
                        "ng aromas, and succulent flavors.";
            }
            else
            {
                //gemini
                image = R.drawable.gemini;
                description = "Have you ever been so busy that you wished you could clone" +
                        " yourself just to get everything done? That's the Gemini experi" +
                        "ence in a nutshell. Spontaneous, playful, and adorably erratic, " +
                        "Gemini is driven by its insatiable curiosity. Appropriately sym" +
                        "bolized by the celestial twins, this air sign was interested in" +
                        " so many pursuits that it had to double itself. You know, NBD!";
            }
        }
        else if (m.equals("Jun"))
        {
            if (d >= 1 && d < 21)
            {
                //gemini
                image = R.drawable.gemini;
                description = "Have you ever been so busy that you wished you could clone" +
                        " yourself just to get everything done? That's the Gemini experi" +
                        "ence in a nutshell. Spontaneous, playful, and adorably erratic, " +
                        "Gemini is driven by its insatiable curiosity. Appropriately sym" +
                        "bolized by the celestial twins, this air sign was interested in" +
                        " so many pursuits that it had to double itself. You know, NBD!";
            }
            else
            {
                //cancer
                image = R.drawable.cancer;
                description = "Represented by the crab, Cancer seamlessly weaves between" +
                        " the sea and shore representing Cancer’s ability to exist in bot" +
                        "h emotional and material realms. Cancers are highly intuitive an" +
                        "d their psychic abilities manifest in tangible spaces. But—just" +
                        " like the hard-shelled crustaceans—this water sign is willing to" +
                        " do whatever it takes to protect itself emotionally. In order to" +
                        " get to know this sign, you're going to need to establish trust!";
            }
        }
        else if (m.equals("Jul"))
        {
            if (d >= 1 && d < 23)
            {
                //cancer
                image = R.drawable.cancer;
                description = "Represented by the crab, Cancer seamlessly weaves between" +
                        " the sea and shore representing Cancer’s ability to exist in bot" +
                        "h emotional and material realms. Cancers are highly intuitive an" +
                        "d their psychic abilities manifest in tangible spaces. But—just" +
                        " like the hard-shelled crustaceans—this water sign is willing to" +
                        " do whatever it takes to protect itself emotionally. In order to" +
                        " get to know this sign, you're going to need to establish trust!";
            }
            else
            {
                //leo
                image = R.drawable.leo;
                description = "Roll out the red carpet because Leo has arrived. Passiona" +
                        "te, loyal, and infamously dramatic, Leo is represented by the l" +
                        "ion and these spirited fire signs are the kings and queens of th" +
                        "e celestial jungle. They're delighted to embrace their royal sta" +
                        "tus: Vivacious, theatrical, and fiery, Leos love to bask in the " +
                        "spotlight and celebrate… well, themselves";
            }
        }
        else if (m.equals("Aug"))
        {
            if (d >= 1 && d < 23)
            {
                //leo
                image = R.drawable.leo;
                description = "Roll out the red carpet because Leo has arrived. Passiona" +
                        "te, loyal, and infamously dramatic, Leo is represented by the l" +
                        "ion and these spirited fire signs are the kings and queens of th" +
                        "e celestial jungle. They're delighted to embrace their royal sta" +
                        "tus: Vivacious, theatrical, and fiery, Leos love to bask in the " +
                        "spotlight and celebrate… well, themselves";
            }
            else
            {
                //virgo
                image = R.drawable.virgo;
                description = "You know the expression, \"if you want something done, " +
                        "give it to a busy person?\" Well, that definitely is the Virg" +
                        "o anthem. Virgos are logical, practical, and systematic in the" +
                        "ir approach to life. Virgo is an earth sign historically repre" +
                        "sented by the goddess of wheat and agriculture, an association" +
                        " that speaks to Virgo's deep-rooted presence in the material wo" +
                        "rld. This earth sign is a perfectionist at heart and isn’t afraid " +
                        "to improve skills through diligent and consistent practice.";
            }
        }
        else if (m.equals("Sep"))
        {
            if (d >= 1 && d < 23)
            {
                //virgo
                image = R.drawable.virgo;
                description = "You know the expression, \"if you want something done, " +
                        "give it to a busy person?\" Well, that definitely is the Virg" +
                        "o anthem. Virgos are logical, practical, and systematic in the" +
                        "ir approach to life. Virgo is an earth sign historically repre" +
                        "sented by the goddess of wheat and agriculture, an association" +
                        " that speaks to Virgo's deep-rooted presence in the material wo" +
                        "rld. This earth sign is a perfectionist at heart and isn’t afraid " +
                        "to improve skills through diligent and consistent practice.";
            }
            else
            {
                //libra
                image = R.drawable.libra;
                description = "Balance, harmony, and justice define Libra energy. As a " +
                        "cardinal air sign, Libra is represented by the scales (interest" +
                        "ingly, the only inanimate object of the zodiac), an association" +
                        " that reflects Libra's fixation on establishing equilibrium. Li" +
                        "bra is obsessed with symmetry and strives to create equilibrium" +
                        " in all areas of life — especially when it comes to matters of " +
                        "the heart.";
            }
        }
        else if (m.equals("Oct"))
        {
            if (d >= 1 && d < 23)
            {
                //libra
                image = R.drawable.libra;
                description = "Balance, harmony, and justice define Libra energy. As a " +
                        "cardinal air sign, Libra is represented by the scales (interest" +
                        "ingly, the only inanimate object of the zodiac), an association" +
                        " that reflects Libra's fixation on establishing equilibrium. Li" +
                        "bra is obsessed with symmetry and strives to create equilibrium" +
                        " in all areas of life — especially when it comes to matters of " +
                        "the heart.";
            }
            else
            {
                //scorpio
                image = R.drawable.scorpio;
                description = "Elusive and mysterious, Scorpio is one of the most" +
                        " misunderstood signs of the zodiac. Scorpio is a water sign " +
                        "that uses emotional energy as fuel, cultivating powerful wis" +
                        "dom through both the physical and unseen realms. In fact, Sc" +
                        "orpio derives its extraordinary courage from its psychic abi" +
                        "lities, which is what makes this sign one of the most complic" +
                        "ated, dynamic signs of the zodiac.";
            }
        }
        else if (m.equals("Nov"))
        {
            if (d >= 1 && d < 22)
            {
                //scorpio
                image = R.drawable.scorpio;
                description = "Elusive and mysterious, Scorpio is one of the most" +
                        " misunderstood signs of the zodiac. Scorpio is a water sign " +
                        "that uses emotional energy as fuel, cultivating powerful wis" +
                        "dom through both the physical and unseen realms. In fact, Sc" +
                        "orpio derives its extraordinary courage from its psychic abi" +
                        "lities, which is what makes this sign one of the most complic" +
                        "ated, dynamic signs of the zodiac.";
            }
            else
            {
                //sagittarius
                image = R.drawable.sagittarius;
                description = "Oh, the places Sagittarius goes! But… actually. This " +
                        "fire sign knows no bounds. Represented by the archer, Sagit" +
                        "tarians are always on a quest for knowledge. The last fire " +
                        "sign of the zodiac, Sagittarius launches its many pursuits " +
                        "like blazing arrows, chasing after geographical, intellectua" +
                        "l, and spiritual adventures.";
            }
        }
        else if (m.equals("Dec"))
        {
            if (d >= 1 && d < 20)
            {
                //sagittarius
                image = R.drawable.sagittarius;
                description = "Oh, the places Sagittarius goes! But… actually. This " +
                        "fire sign knows no bounds. Represented by the archer, Sagit" +
                        "tarians are always on a quest for knowledge. The last fire " +
                        "sign of the zodiac, Sagittarius launches its many pursuits " +
                        "like blazing arrows, chasing after geographical, intellectua" +
                        "l, and spiritual adventures.";

            }
            else
            {
                //capricorn
                image = R.drawable.capricorn;
                description = "What is the most valuable resource? For Capricorn, " +
                        "the answer is clear: Time. Capricorn is climbing the mounta" +
                        "in straight to the top and knows that patience, perseveranc" +
                        "e, and dedication is the only way to scale. The last earth s" +
                        "ign of the zodiac, Capricorn, is represented by the sea-goat" +
                        ", a mythological creature with the body of a goat and the ta" +
                        "il of a fish. Accordingly, Capricorns are skilled at navigati" +
                        "ng both the material and emotional realms.";
            }
        }
        System.out.println("image " + image);
        return image;
    }
}



