package auxesisapp.anjanproduction.com.auxesis;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CategoriesDescriptor extends AppCompatActivity {
    private int finalPos;
    private ListView listView;
    private double latng;
    private double longi;

    private ArrayList<detailsModel> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_descriptor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.categ_lv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("EVENTS");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        checkAndExecute();


    }

    private void checkAndExecute() {
        Home home = new Home();
        finalPos=home.getData();
        switch (finalPos){
            case 0:

                initSocialEvents();
                detailsModelAdapter adapter=new detailsModelAdapter(this,list);
                listView.setAdapter(adapter);
                break;

            case 1:

                initTechnicalEvents();
                detailsModelAdapter adapter1=new detailsModelAdapter(this,list);
                listView.setAdapter(adapter1);
                break;

            case 2:
                initCreativeEvents();
                detailsModelAdapter adapter2=new detailsModelAdapter(this,list);
                listView.setAdapter(adapter2);
                break;

            case 3:
               initSportsEvents();
                detailsModelAdapter adapter3=new detailsModelAdapter(this,list);
                listView.setAdapter(adapter3);
                break;

            case 4:
                initSchoolAndManagement();
                detailsModelAdapter adapter4=new detailsModelAdapter(this,list);
                listView.setAdapter(adapter4);
                break;

            case 5:
                initCulturalEvents();
                detailsModelAdapter adapter5=new detailsModelAdapter(this,list);
                listView.setAdapter(adapter5);
                break;
        }

    }

    private void initCulturalEvents() {

        detailsModel model=new detailsModel();
        model.setName("EUPHONICS");
        model.setDescp("DAY 1:\n\n" +
                "EVENT NAME:EUPHONICS\n\n" +
                "EVENT DETAILS:Solo or group singing competition\n\n" +
                "VENUE:DUIET CORE BUILDING\n\n" +
                "TIME:10AM to 1PM");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET CORE BUILDING");
        LatLng spot=new LatLng(27.451517, 94.890427);
        model.setPlace(spot);
        model.setPlaceName("DUIET Core Building");
        list.add(model);

        model=new detailsModel();
        model.setName("CULTURAL EXCHANGE");
        LatLng br=new LatLng(27.449558, 94.896223);
        model.setDescp("DAY 1:\n\n" +
                "EVENT NAME:CULTURAL EXCHANGE\n\n" +
                "EVENT DETAILS:Folk or Contempory Dance Competition\n\n" +
                "VENUE:BHISHNURABHA COMMUNITY HALL\n\n" +
                "TIME:1PM to 4PM");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET CORE BUILDING");
        //LatLng spot1=new LatLng(27.451517, 94.890427);
        model.setPlace(br);
        model.setPlaceName("Bishnurabha Community Hall");
        list.add(model);


        model=new detailsModel();
        model.setName("ROOTS");
        model.setDescp("DAY 2:\n\n" +
                "EVENT NAME:ROOTS\n\n" +
                "EVENT DETAILS:Bihu competition\n\n" +
                "VENUE:BISHNURABHA COMMUNITY HALL\n\n" +
                "TIME:10AM to 2PM");

        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("Bishnurabha Community Hall");
        LatLng spot1=new LatLng(27.451517, 94.890427);
        model.setPlace(br);
        list.add(model);

        model=new detailsModel();
        model.setName("RANSACK");
        model.setDescp("DAY 2:\n\n" +
                "EVENT NAME:RANSACK\n\n" +
                "EVENT DETAILS:BATTLE OF BANDS\n\n" +
                "VENUE:OLD PLAYGROUND\n\n" +
                "TIME:5PM ONWARDS");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("Old PlayGround");
        LatLng old=new LatLng(27.449611, 94.892896);
        model.setPlace(old);
        list.add(model);

        model=new detailsModel();
        model.setName("GO STREETZ");
        model.setDescp("DAY 3:\n\n" +
                "EVENT NAME:GO STREETZ\n\n" +
                "EVENT DETAILS:Free Style Dance Competition\n\n" +
                "VENUE:DUIET OPEN GALLERY\n\n" +
                "TIME:10AM-1PM");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET OPEN GALLERY");
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("KALLISTEA");
        model.setDescp("DAY 3:\n\n" +
                "EVENT NAME:KALLISTEA\n\n" +
                "EVENT DETAILS:Fashion Competition\n\n" +
                "VENUE:BISHNU RABHA COMMUNITY HALL\n\n" +
                "TIME:1PM-5PM");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("Bishnu Rabha Community Hall");

        model.setPlace(br);
        list.add(model);

        model=new detailsModel();
        model.setName("CHROMATIC WALTZ ");
        model.setDescp("DAY 3:\n\n" +
                "EVENT NAME:CHROMATIC WALTZ\n\n" +
                "EVENT DETAILS:TWO ARTIST FOR LAST NIGHT\n\n" +
                "VENUE:OLD PLAYGROUND\n\n" +
                "TIME:5PM ONWARDS");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlace(old);
        model.setPlaceName("Old Playground");
        list.add(model);

        model=new detailsModel();
        model.setName("AUXONOMMIA ");
        model.setDescp("DAY 3:\n\n" +
                "EVENT NAME:AUXONOMMIA\n\n" +
                "EVENT DETAILS:DJ NIGHT\n\n" +
                "VENUE:OLD PLAYGROUND\n\n" +
                "TIME:9PM ONWARDS");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlace(old);
        model.setPlaceName("Old Playground");
        list.add(model);



    }

    private void initSchoolAndManagement() {

        detailsModel model=new detailsModel();
        model.setName("INQUIZON");
        model.setDescp("School level Quiz Competition.To showcase your knowledge about various topics and trends.\n" +
                "\n" +
                "Age Group :   \n" +
                "\n" +
                "a)Class 5 to 8\n" +
                "\n" +
                "b)Class 9 to 12\n" +
                "\n" +
                "TIME : 12.30pm-1.45pm, Day 3\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        LatLng spot4=new LatLng(27.451201, 94.891265);
        model.setPlace(spot4);
        model.setPlaceName("DUIET Mechanical Building");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);

        model=new detailsModel();
        model.setName("PROJECTILE");
        model.setDescp("PowerPoint Presentation on a topic given few weeks ahead of the event.\n" +
                "\n" +
                "Age Group : ALL\n" +
                "\n" +
                "TIME : 12pm – 1.30pm, Day 1\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        model.setPlaceName("DUIET Mechanical Building");
        LatLng spot=new LatLng(27.451201, 94.891265);
        model.setPlace(spot);
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);


        model=new detailsModel();
        model.setName("CRACK THE INTERVIEW");
        model.setDescp("Mock Interview. First step towards being an professional career.\n" +
                "\n" +
                "Age Group : ALL\n" +
                "\n" +
                "TIME : 9.30am – 11.30am,  Day 1\n" +

                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        LatLng spot5=new LatLng(27.451201, 94.891265);
        model.setPlace(spot5);
        model.setPlaceName("DUIET Mechanical Bulding");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);

        model=new detailsModel();
        model.setName("TRIVIAL PARADOX");
        model.setDescp("Quiz Competition. To showcase your knowledge about various topics and trends where Quiz Master will be Major Chandrakant Nair.\n" +
                "\n" +
                "Age Group : ALL\n" +
                "\n" +
                "TIME : 9.30pm-4pm, Day 2\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        LatLng spot3=new LatLng(27.448388, 94.896372);
        model.setPlace(spot3);
        model.setPlaceName("Indira Miri Conference Hall");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);

        model=new detailsModel();
        model.setName("WAR WORDS");
        model.setDescp("School Level Debate Competition. To showcase your public speaking. The confidence you have towards the topic either for the motion or against the motion.\n" +
                "\n" +
                "Age Group : ALL\n" +
                "\n" +
                "TIME  : 2pm-4pm, Day 1\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        LatLng spot1=new LatLng(27.451201, 94.891265);
        model.setPlace(spot);
        model.setPlaceName("DUIET Mechanical Building");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);


        model=new detailsModel();
        model.setName("CAN YOU SELL IT?");
        model.setDescp("Selling of different objects. To showcase your presentation skill by attracting more people towards your motive.\n" +
                "\n" +
                "Age Group : ALL\n" +
                "\n" +
                "TIME : 4.30pm-5.30pm, Day 1\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        model.setPlaceName("University Premises");
        LatLng m=new LatLng(27.451378, 94.890546);
        model.setPlace(m);
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);



        model=new detailsModel();
        model.setName("OPEN MIC");
        model.setDescp("POETRY AND STAND UP COMEDY \nAge Group : ALL\n" +
                "\n" +
                "TIME : 3.30pm-7pm ,  9-nov-2018\nContact :  " +
                "Dipankar Bezbaruah – 7576831134");
        LatLng spot2=new LatLng(27.451201, 94.891265);
        model.setPlace(spot);
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET Mechanical Building");
        list.add(model);

        model=new detailsModel();
        model.setName("VIBGYOR");
        model.setDescp("On the spot Drawing competition. To showcase your imagination skills and present them as an art.\n" +
                "\n" +
                "Age Group : \n" +
                "\n" +
                "a)Age 5 to 12\n" +
                "\n" +
                "b)Age 13 to 18\n" +
                "\n" +
                "c)Age 19 and above\n" +
                "\n" +
                "TIME : 12.30pm-1.45pm, Day 3\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        model.setPlaceName("DUIET Mechanical Building");
        model.setPlace(spot2);
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);

        model=new detailsModel();
        model.setName("CINEVISION");
        model.setDescp("Summarizing on the spot idea on mute clip that will be played during the event.\n" +
                "\n" +
                "Age Group :  ALL\n" +
                "\n" +
                "TIME : 2pm-3pm, Day 3\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        model.setPlaceName("DUIET Mechanical Building");
        model.setPlace(spot2);
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);

        model=new detailsModel();
        model.setName("DIALECTIC");
        model.setDescp("College level/University level debate competition.To showcase your public speaking. The confidence you have towards the topic either for the motion or against the motion.\n" +
                "\n" +
                "Age Group : Age 18 and Above\n" +
                "\n" +
                "TIME : 3pm-5pm, Day 3\n" +
                "\n" +
                "Contact : Dipankar Bezbaruah – 7576831134");
        model.setPlace(spot2);
        model.setPlaceName("DUIET Mechanical Building");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        list.add(model);





    }

    private void initSportsEvents() {

        detailsModel model=new detailsModel();
        model.setName("HATTRICK");
        model.setDescp("It is a Prize money tennis ball cricket tournament of 5 overs per innings . Only 1 bowler is allowed a maximum of 2 overs, rest of the 3 overs can be shared by other bowlers.\n" +
                "\n" +
                "RULES:-\n" +
                "\n" +
                "There will be no LBWs & Leg Byes.\n" +
                "5 overs per innings\n" +
                "3rd over will be the power over , means in that over score will be doubled.\n" +
                "8 players per team\n" +
                "Cricket Bat will not provided by the committee.\n" +
                " Venue :  New playground\n" +
                "\n" +
                "Contact  :\n" +
                "\n" +
                "          Prahllad Chetia  –  8133896340\n" +
                "\n" +
                "          Priyankush Dutta  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("New PlayGround");
        LatLng spot=new LatLng(27.448686, 94.900579);
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("THIRD POCKET");
        model.setDescp("Prize Money Carom tournament of doubles and singles. It will be held both boys and girls.\n" +
                "\n" +
                " Venue :  New playground\n" +
                "\n" +
                "Contact  :\n" +
                "\n" +
                "Priyankush Dutta  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("New PlayGround");
        model.setPlace(spot);
        list.add(model);


        model=new detailsModel();
        model.setName("JOGA BONITO");
        model.setDescp("5 a side Futsal Tournament\n" +
                "\n" +
                "It is a prize money futsal tournament of 5 players each side.\n" +
                "\n" +
                "RULES:- 1. Each team should register a maximum amount of 6 players\n" +
                "\n" +
                "Total 20 minutes consisting two half of 10 minutes each\n" +
                "Player receiving Yellow card will be subbed for 2 minutes\n" +
                "Rolling Substitution\n" +
                " Venue :  New playground\n" +
                "\n" +
                "Contact  :\n" +
                "\n" +
                "Mrigang Tayeng  –  9127395811");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("New PlayGround");
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("MINTONETTE");
        model.setDescp("It is a PRIZE MONEY Volleyball tournament.Volleyball is a team sport in which two teams of six players are separated by a net. Each team tries to score points by grounding a ball on the other team’s court under organized rules.\n" +
                "\n" +
                "RULES:-\n" +
                "\n" +
                "6 players on the floor at any one time – 3 in the front row and 3 in the back row\n" +
                "Maximum of 3 hits per side\n" +
                "Points are made on every serve for wining team of rally (rally-point scoring).\n" +
                "Player may not hit the ball twice in succession. (A block is not considered a hit.)\n" +
                "Ball may be played off the net during a volley and on a serve.\n" +
                "A ball hitting a boundary line is in.\n" +
                "It is legal to contact the ball with any part of a player’s body.\n" +
                "It is illegal to catch, hold or throw the ball.\n" +
                "A player cannot block or attack a serve from on or inside the 10-foot line.\n" +
                "After the serve, front-line players may switch positions at the net.\n" +
                " Venue :  New playground\n" +
                "\n" +
                "Contact  :\n" +
                "\n" +
                "Priyankush Dutta  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("New PlayGround");
        model.setPlace(spot);
        list.add(model);
    }

    private void initCreativeEvents() {
        detailsModel model=new detailsModel();
        model.setName("ART EXPO ");
        model.setDescp("Exhibition cum sale of art and crafts (categorized on the type of art). A special category for crafts made by orphanage children will be on display as well in order to encourage them.\n" +
                "\n" +
                " \n" +
                "\n" +
                " Venue :       Core Building, DUIET\n" +
                "\n" +
                "Contact  :\n" +
                "\n" +
                "          Donald Bikki Urang  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        LatLng spot=new LatLng(27.451517, 94.890427);
        model.setPlaceName("DUIET Core Building");
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("WASTE TO WEALTH");
        model.setDescp("In todays world where waste is increasing day by day, we the team of Auxesis encourage the youth to get creative and build something out of waste products in a limited period of time.\n" +
                "\n" +
                " Venue :            Core Building, DUIET\n" +
                "\n" +
                "Contact :          \n" +
                "\n" +
                "Donald Bikki Urang  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET Core Building");
        model.setPlace(spot);
        list.add(model);


        model=new detailsModel();
        model.setName("SNAPSHOT");
        model.setDescp("An on-spot photography competition.  The best photographs will be uploaded to our Facebook and Instagram pages.\n" +
                "\n" +
                " Venue :            Core Building, DUIET\n" +
                "\n" +
                "Contact :         \n" +
                "\n" +
                " Donald Bikki Urang  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET Core Building");
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("T-SHIRT PAINTING");
        model.setDescp("An on-spot theme-based t-shirt painting competition where imagination is limitless and boundary is defined only by your creativity.\n" +
                "\n" +
                " Venue :            Core Building, DUIET\n" +
                "\n" +
                "Contact :          \n" +
                "\n" +
                "    Donald Bikki Urang  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET Core Building");
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("DOODLE ART");
        model.setDescp("The Doodle Art competition is also an on-spot theme-based doodle making competition. This competition is for those whodo not just see doodlingas a mere drawing activity, but also believe it has a depth of meaning and style.\n" +
                "\n" +
                " Venue :            Core Building, DUIET\n" +
                "\n" +
                "Contact :          Donald Bikki Urang  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET Core Building");
        model.setPlace(spot);
        list.add(model);

        model=new detailsModel();
        model.setName("ABSTRACT");
        model.setDescp("Abstract is an art competition. Abstract is categorized on the basis of age, the details of which will be uploaded soon. Participants from every age group are invited.\n" +
                "\n" +
                " Venue :            Core Building, DUIET\n" +
                "\n" +
                "Contact :          \n" +
                "\n" +
                "       Donald Bikki Urang  – 8486529946");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("DUIET Core Building");
        model.setPlace(spot);
        list.add(model);



    }

    private void initTechnicalEvents() {

        detailsModel model=new detailsModel();
        model.setName("ROBO SOCCER");
        model.setDescp(": Everyone might be a FOOTBALL FREAK...one might rule on the field or slay in FIFA, but this competition is a class apart!! It's a unique test of one’s skills of Robotics and ones passion for Soccer.... Teams build their own bots capable of moving around and netting balls into the goals. \n" +
                "So get ready with Soccer Bots to clash against each other in the special ROBOSOCCER ARENA and score maximum goals to take home Attractive prizes. \n" +
                "Prizes worth- ₹16000\n ");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);

        model=new detailsModel();
        model.setName("ROBO ASSAULT");
        model.setDescp("Welcome to the Arena of Assault where the Deadly Mean Machines take on each other Head to Head until one of them is destroyed or banished from the Arena! \n" +
                "So is it in you to build the ULTIMATE BOT OF DESTRUCTION to take on all your opponents and prove everyone. The Annual robotics assault competition. Teams from all over Assam battle for the ultimate prize. In this edition expect something different and challenging.\n" +
                "Prizes Worth- ₹16,000\n");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);

        model=new detailsModel();
        model.setName("ADROIT.VIE");
        model.setDescp("The Mega Gaming Extravaganza of Auxesis. Participate and witness the extraordinary competition amongst the best gaming teams of Assam. E-sports begin the emerging sports genre of India Adroit. Vie is the platform for the budding gamers to showcase their ultimate gaming skills.\n" +
                "Prizes Worth- ₹90,000\n");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);

        model=new detailsModel();
        model.setName("ROBOTICS");
        model.setDescp("With the advancement of technology knowledge of Robotics is a must. Auxesis V8.0 aims to provide the upcoming engineers with the essential knowledge of robotics to prosper in the careers.\n" +
                "Registration fee- ₹1500\n");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);

        model=new detailsModel();
        model.setName("MACHINE LEARNING");
        model.setDescp("Have you ever wondered how Facebook, Google, Microsoft are providing such advanced product services? Perhaps a 3-day workshop on Machine Learning will definitely help you to grasp the in demand industry knowledge and get a formidable foot hold in your career.");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);

        model=new detailsModel();
        model.setName("PHOTOGRAPHY");
        model.setDescp("“Capturing the moment”. It is every photographer’s dream to capture the slightest moment in the lens. Learn from the experts the basics of photography and capture the beautiful moments.");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);

        model=new detailsModel();
        model.setName("CUBICON");
        model.setDescp("This year Auxesis is setting the stage by collaborating with World Cube Association. We invite every folk having the dream to set a record and put their names in the World Cube Association.\n" +
                "Prizes Worth- ₹15000\n");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlaceName("ECE Department");
        model.setPlace(new LatLng(27.451781, 94.891423));
        list.add(model);


    }

    private void initSocialEvents() {

        detailsModel model=new detailsModel();
        model.setName("TREE PLANTATION CUM AWARENESS EVENT");
        model.setDescp("A mega plantation event for a greener future.\n" +
                "CHIEF GUEST: Jadhav Maloi Payeng ");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        LatLng spot=new LatLng(27.451517, 94.890427);
        model.setPlace(spot);
        model.setPlaceName("DUIET Core Building");
        list.add(model);

        model=new detailsModel();
        model.setName("DIBRU RUN(PINKATHON)");
        model.setDescp("A marathon event for the fresh and active youths ");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlace(spot);
        model.setPlaceName("DUIET Core Building");
        list.add(model);


        model=new detailsModel();
        model.setName("STREET PLAY ON RAPE CULTURE AND VICTIM SHAMING ");
        model.setDescp("A live street performance ");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlace(spot);
        model.setPlaceName("DUIET Core Building");
        list.add(model);

        model=new detailsModel();
        model.setName("Orphanage and old age home visits ,and medical camp ");
        model.setDescp(" In CHAOLKHOWA VILLAGE ,BOGIBEEL");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlace(spot);
        model.setPlaceName("DUIET Core Building");
        list.add(model);

        model=new detailsModel();
        model.setName("CYCLE RALLY");
        model.setDescp("INSPIRING YOUTHS FOR POLLUTONLESS TRANSPORTATION");
        model.setDrawable(R.drawable.baseline_favorite_border_black_36dp);
        model.setPlace(spot);
        model.setPlaceName("DUIET Core Building");
        list.add(model);


    }
    public ArrayList<detailsModel> GetArrayList(String key){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(CategoriesDescriptor.this);
        Gson gson=new Gson();
        String json=preferences.getString(key,null);
        Type type=new TypeToken<ArrayList<detailsModel>>(){}.getType();
        return gson.fromJson(json,type);
    }
}
