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

//this class helps retrieve and manage data across fragments
//Angel and Sam
public class HoroscopeAdapter extends RecyclerView.Adapter<HoroscopeAdapter.MyViewHolder> {

    //private Context context;
    private ArrayList<UserInfo> allPeople;
    private ImageView imvDelete;
    private ImageView imvSign;
    private ImageView imvUserSign;
    private View itemView;
    public String description;
    public String link;

    private FragmentManager fragmentManager;

    //Set fragment and users
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

        //Get postion of user
        UserInfo p = allPeople.get(position);
        holder.setData(p, position);

        //System.out.println("DONE CREATING POPULATING A ROW: " + position + " " + p.getTitleNote());
    }

    //Number of users
    @Override
    public int getItemCount() {

        return allPeople.size();
    }

    //Set the users
    public void setUsers(ArrayList<UserInfo> users) {
        allPeople = users;
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


            //Delete added users
            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Create dbhelper
                    DBHelper dbHelper = new DBHelper(view.getContext());
                    dbHelper.deleteUser(currentPerson);

                    ArrayList<UserInfo> tempList = dbHelper.fetchAllUsers();

                    //If not the main user remove user
                    if (currentPerson.getId() != 1) {
                        allPeople.remove(currentPositionInList);
                        notifyItemRemoved(currentPositionInList);
                        notifyItemRangeChanged(currentPositionInList, allPeople.size());
                    }
                }
            });
        }


        //Set the data for the user in textviews
        public void setData(UserInfo p, int pos) {

            int sign;
            currentPositionInList = pos;
            currentPerson = p;
            System.out.println("THIS IS P" + p);
            TextView name = itemView.findViewById(R.id.tvName);
            name.setText(p.getName());
            TextView dob = itemView.findViewById(R.id.tvBirthday);
            dob.setText(getFormattedDate(p.getBirthday()) + "");
            imvSign = itemView.findViewById(R.id.iv_sign_row);
            System.out.println("I WORK UP TO HERE");
            long bday = p.getBirthday();
            String formBday = getFormattedDate(bday);
            sign = getImage(formBday);
            imvSign.setImageResource(sign);


        }


        @Override
        public void onClick(View view) {
            DialogIndividInfo dialog = new DialogIndividInfo(currentPerson, HoroscopeAdapter.this);
            dialog.show(fragmentManager, "");
        }
    }

    //Format the miliseconds to date
    private String getFormattedDate(long dobInMilis) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        Date dobDate = new Date(dobInMilis);
        String s = formatter.format(dobDate);
        return s;
    }

    //Get the image Description and link for the user
    public int getImage(String b) {
        int image = 0;
        String[] date = b.split("/");
        int d = Integer.parseInt(date[0]);
        String m = date[1];

        //ImageView image = itemView.findViewById(R.id.iv_sign);

        if (m.equals("Jan")) {
            if (d >= 1 && d < 20) {
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
                link = "https://www.mindbodygreen.com/articles/capricorn#:~:" +
                        "text=Capricorn%20is%20the%2010th%20sign,energy%20is%20honest%20and%20wise.";

            } else {
                //aquarius
                image = R.drawable.aquarius;
                description = "Despite the \"aqua\" in its name, Aquarius is actually " +
                        "the last air sign of the zodiac. Innovative, progressive, and" +
                        " shamelessly revolutionary, Aquarius is represented by the wat" +
                        "er bearer, the mystical healer who bestows water, or life, upo" +
                        "n the land. Accordingly, Aquarius is the most humanitarian astr" +
                        "ological sign. At the end of the day, Aquarius is dedicated to" +
                        " making the world a better place.";
                link = "https://www.allure.com/story/aquarius-zodiac-sign-personality-traits";
            }
        } else if (m.equals("Feb")) {
            if (d >= 1 && d < 19) {
                //aquarius
                image = R.drawable.aquarius;
                description = "Despite the \"aqua\" in its name, Aquarius is actually " +
                        "the last air sign of the zodiac. Innovative, progressive, and" +
                        " shamelessly revolutionary, Aquarius is represented by the wat" +
                        "er bearer, the mystical healer who bestows water, or life, upo" +
                        "n the land. Accordingly, Aquarius is the most humanitarian astr" +
                        "ological sign. At the end of the day, Aquarius is dedicated to" +
                        " making the world a better place.";
                link = "https://www.allure.com/story/aquarius-zodiac-sign-personality-traits";
            } else {
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

                link = "https://www.costarastrology.com/zodiac-signs/pisces-sign";
            }
        } else if (m.equals("Mar")) {
            if (d >= 1 && d < 21) {
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

                link = "https://www.costarastrology.com/zodiac-signs/pisces-sign";
            } else {
                //aries
                image = R.drawable.aries;
                description = "The first sign of the zodiac, Aries loves to be number one." +
                        " Naturally, this dynamic fire sign is no stranger to competition." +
                        " Bold and ambitious, Aries dives headfirst into even the most cha" +
                        "llenging situations—and they'll make sure they always come out on" +
                        " top!";
                link = "https://www.allure.com/story/aries-zodiac-sign-personality-traits";
            }
        } else if (m.equals("Apr")) {
            if (d >= 1 && d < 20) {
                //aries
                image = R.drawable.aries;
                description = "The first sign of the zodiac, Aries loves to be number one." +
                        " Naturally, this dynamic fire sign is no stranger to competition." +
                        " Bold and ambitious, Aries dives headfirst into even the most cha" +
                        "llenging situations—and they'll make sure they always come out on" +
                        " top!";
                link = "https://www.allure.com/story/aries-zodiac-sign-personality-traits";
            } else {
                //taurus
                image = R.drawable.taurus;
                description = "What sign is more likely to take a six-hour bath, followed" +
                        " by a luxurious Swedish massage and decadent dessert spread? Why" +
                        " Taurus, of course! Taurus is an earth sign represented by the bu" +
                        "ll. Like their celestial spirit animal, Taureans enjoy relaxing i" +
                        "n serene, bucolic environments surrounded by soft sounds, soothi" +
                        "ng aromas, and succulent flavors.";
                link = "https://www.costarastrology.com/zodiac-signs/taurus-sign";
            }
        } else if (m.equals("May")) {
            if (d >= 1 && d < 21) {
                //taurus
                image = R.drawable.taurus;
                description = "What sign is more likely to take a six-hour bath, followed" +
                        " by a luxurious Swedish massage and decadent dessert spread? Why" +
                        " Taurus, of course! Taurus is an earth sign represented by the bu" +
                        "ll. Like their celestial spirit animal, Taureans enjoy relaxing i" +
                        "n serene, bucolic environments surrounded by soft sounds, soothi" +
                        "ng aromas, and succulent flavors.";
                link = "https://www.costarastrology.com/zodiac-signs/taurus-sign";
            } else {
                //gemini
                image = R.drawable.gemini;
                description = "Have you ever been so busy that you wished you could clone" +
                        " yourself just to get everything done? That's the Gemini experi" +
                        "ence in a nutshell. Spontaneous, playful, and adorably erratic, " +
                        "Gemini is driven by its insatiable curiosity. Appropriately sym" +
                        "bolized by the celestial twins, this air sign was interested in" +
                        " so many pursuits that it had to double itself. You know, NBD!";
                link = "https://www.zodiacsign.com/zodiac-signs/gemini/";
            }
        } else if (m.equals("Jun")) {
            if (d >= 1 && d < 21) {
                //gemini
                image = R.drawable.gemini;
                description = "Have you ever been so busy that you wished you could clone" +
                        " yourself just to get everything done? That's the Gemini experi" +
                        "ence in a nutshell. Spontaneous, playful, and adorably erratic, " +
                        "Gemini is driven by its insatiable curiosity. Appropriately sym" +
                        "bolized by the celestial twins, this air sign was interested in" +
                        " so many pursuits that it had to double itself. You know, NBD!";
                link = "https://www.zodiacsign.com/zodiac-signs/gemini/";
            } else {
                //cancer
                image = R.drawable.cancer;
                description = "Represented by the crab, Cancer seamlessly weaves between" +
                        " the sea and shore representing Cancer’s ability to exist in bot" +
                        "h emotional and material realms. Cancers are highly intuitive an" +
                        "d their psychic abilities manifest in tangible spaces. But—just" +
                        " like the hard-shelled crustaceans—this water sign is willing to" +
                        " do whatever it takes to protect itself emotionally. In order to" +
                        " get to know this sign, you're going to need to establish trust!";
                link = "https://www.mindbodygreen.com/articles/cancer-sign-101#:~" +
                        ":text=Cancer%20is%20the%20fourth%20sign,emotional%2C%20homey%2C%20and%20comforting.";
            }
        } else if (m.equals("Jul")) {
            if (d >= 1 && d < 23) {
                //cancer
                image = R.drawable.cancer;
                description = "Represented by the crab, Cancer seamlessly weaves between" +
                        " the sea and shore representing Cancer’s ability to exist in bot" +
                        "h emotional and material realms. Cancers are highly intuitive an" +
                        "d their psychic abilities manifest in tangible spaces. But—just" +
                        " like the hard-shelled crustaceans—this water sign is willing to" +
                        " do whatever it takes to protect itself emotionally. In order to" +
                        " get to know this sign, you're going to need to establish trust!";
                link = "https://www.mindbodygreen.com/articles/cancer-sign-101#:~" +
                        ":text=Cancer%20is%20the%20fourth%20sign,emotional%2C%20homey%2C%20and%20comforting.";
            } else {
                //leo
                image = R.drawable.leo;
                description = "Roll out the red carpet because Leo has arrived. Passiona" +
                        "te, loyal, and infamously dramatic, Leo is represented by the l" +
                        "ion and these spirited fire signs are the kings and queens of th" +
                        "e celestial jungle. They're delighted to embrace their royal sta" +
                        "tus: Vivacious, theatrical, and fiery, Leos love to bask in the " +
                        "spotlight and celebrate… well, themselves";
                link = "https://www.britannica.com/place/Leo-constellation";
            }
        } else if (m.equals("Aug")) {
            if (d >= 1 && d < 23) {
                //leo
                image = R.drawable.leo;
                description = "Roll out the red carpet because Leo has arrived. Passiona" +
                        "te, loyal, and infamously dramatic, Leo is represented by the l" +
                        "ion and these spirited fire signs are the kings and queens of th" +
                        "e celestial jungle. They're delighted to embrace their royal sta" +
                        "tus: Vivacious, theatrical, and fiery, Leos love to bask in the " +
                        "spotlight and celebrate… well, themselves";
                link = "https://www.britannica.com/place/Leo-constellation";
            } else {
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
                link = "https://www.allure.com/story/virgo-zodiac-sign-personality-traits";
            }
        } else if (m.equals("Sep")) {
            if (d >= 1 && d < 23) {
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
                link = "https://www.allure.com/story/virgo-zodiac-sign-personality-traits";
            } else {
                //libra
                image = R.drawable.libra;
                description = "Balance, harmony, and justice define Libra energy. As a " +
                        "cardinal air sign, Libra is represented by the scales (interest" +
                        "ingly, the only inanimate object of the zodiac), an association" +
                        " that reflects Libra's fixation on establishing equilibrium. Li" +
                        "bra is obsessed with symmetry and strives to create equilibrium" +
                        " in all areas of life — especially when it comes to matters of " +
                        "the heart.";
                link = "https://www.horoscope.com/zodiac-signs/libra";
            }
        } else if (m.equals("Oct")) {
            if (d >= 1 && d < 23) {
                //libra
                image = R.drawable.libra;
                description = "Balance, harmony, and justice define Libra energy. As a " +
                        "cardinal air sign, Libra is represented by the scales (interest" +
                        "ingly, the only inanimate object of the zodiac), an association" +
                        " that reflects Libra's fixation on establishing equilibrium. Li" +
                        "bra is obsessed with symmetry and strives to create equilibrium" +
                        " in all areas of life — especially when it comes to matters of " +
                        "the heart.";
                link = "https://www.horoscope.com/zodiac-signs/libra";
            } else {
                //scorpio
                image = R.drawable.scorpio;
                description = "Elusive and mysterious, Scorpio is one of the most" +
                        " misunderstood signs of the zodiac. Scorpio is a water sign " +
                        "that uses emotional energy as fuel, cultivating powerful wis" +
                        "dom through both the physical and unseen realms. In fact, Sc" +
                        "orpio derives its extraordinary courage from its psychic abi" +
                        "lities, which is what makes this sign one of the most complic" +
                        "ated, dynamic signs of the zodiac.";

                link = "https://www.instyle.com/lifestyle/scorpio-zodiac-sign";
            }
        } else if (m.equals("Nov")) {
            if (d >= 1 && d < 22) {
                //scorpio
                image = R.drawable.scorpio;
                description = "Elusive and mysterious, Scorpio is one of the most" +
                        " misunderstood signs of the zodiac. Scorpio is a water sign " +
                        "that uses emotional energy as fuel, cultivating powerful wis" +
                        "dom through both the physical and unseen realms. In fact, Sc" +
                        "orpio derives its extraordinary courage from its psychic abi" +
                        "lities, which is what makes this sign one of the most complic" +
                        "ated, dynamic signs of the zodiac.";
                link = "https://www.instyle.com/lifestyle/scorpio-zodiac-sign";
            } else {
                //sagittarius
                image = R.drawable.sagittarius;
                description = "Oh, the places Sagittarius goes! But… actually. This " +
                        "fire sign knows no bounds. Represented by the archer, Sagit" +
                        "tarians are always on a quest for knowledge. The last fire " +
                        "sign of the zodiac, Sagittarius launches its many pursuits " +
                        "like blazing arrows, chasing after geographical, intellectua" +
                        "l, and spiritual adventures.";

                link = "https://www.britannica.com/place/Sagittarius-constellation";
            }
        } else if (m.equals("Dec")) {
            if (d >= 1 && d < 20) {
                //sagittarius
                image = R.drawable.sagittarius;
                description = "Oh, the places Sagittarius goes! But… actually. This " +
                        "fire sign knows no bounds. Represented by the archer, Sagit" +
                        "tarians are always on a quest for knowledge. The last fire " +
                        "sign of the zodiac, Sagittarius launches its many pursuits " +
                        "like blazing arrows, chasing after geographical, intellectua" +
                        "l, and spiritual adventures.";

                link = "https://www.britannica.com/place/Sagittarius-constellation";
            } else {
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
                link = "https://www.mindbodygreen.com/articles/capricorn#" +
                        ":~:text=Capricorn%20is%20the%2010th%20sign,energy%20is%20honest%20and%20wise.";
            }
        }
        System.out.println("image " + image);
        return image;
    }


}



