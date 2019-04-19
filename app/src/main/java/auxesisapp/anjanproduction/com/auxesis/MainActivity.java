package auxesisapp.anjanproduction.com.auxesis;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.content.pm.PackageManager;


import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static auxesisapp.anjanproduction.com.auxesis.R.drawable.nonactive_dot;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private int STORAGE_PERMISSION_CODE = 1;
    private TabLayout tabLayout;
    private ViewPager imageslider;
    private LinearLayout linearLayout;
    private int dotCount;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private ImageView[] dots;
    public static final int MY_PERMISSION_REQUEST_WRITE=23;
    private ArrayList<notoficationModel> list;
    private int icon[]={R.drawable.baseline_home_black_36dp,R.drawable.baseline_language_black_36dp,R.drawable.baseline_favorite_border_black_36dp};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager=(ViewPager)findViewById(R.id.pager);
        imageslider=(ViewPager)findViewById(R.id.imageSlide);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        linearLayout=(LinearLayout)findViewById(R.id.imageDots);
        viewPager.setAdapter(new SectionPageAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        ImageSliderAdapter adapter=new ImageSliderAdapter(this);
        imageslider.setAdapter(adapter);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
        dotCount=adapter.getCount();
        list=getTasksFromSP(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          /* if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "You have already granted this permission!",
                        Toast.LENGTH_SHORT).show();
            } else {
                requestStoragePermission();
                Toast.makeText(this,"REQUESTING",Toast.LENGTH_LONG).show();
            }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestLocationPermission();
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestWritePermission();
        }
*/requestWritePermission();
        }


        if(list==null){
            list=new ArrayList<>();
        }

        if (getIntent().getExtras() != null){
            String ti=getIntent().getStringExtra("title");
            String bo=getIntent().getStringExtra("body");
            notoficationModel model=new notoficationModel(ti,bo);
            list.add(model);
            saveTasksToSP(this,list);
        }
        dots=new ImageView[dotCount];

        tabLayout.setupWithViewPager(viewPager);
        for(int i=0;i<tabLayout.getTabCount();++i){
            tabLayout.getTabAt(i).setIcon(icon[i]);
        }

        for(int i=0;i<dotCount;i++){
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,0);
            linearLayout.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        imageslider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<dotCount;++i){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



       /* if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestLocationPermission();
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestWritePermission();
        }*/


        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void requestWritePermission() {
       /* if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Writing permission is requied for this app. Otherwise the app may not function properly.Grant permission?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_WRITE);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
            Button buttonbackground1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonbackground1.setBackgroundColor(Color.BLUE);
            Button buttonbackground = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            buttonbackground.setBackgroundColor(Color.BLUE);

        }
        */
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_WRITE);
    }

    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (imageslider.getCurrentItem() == 0) {
                        imageslider.setCurrentItem(1);
                    } else if (imageslider.getCurrentItem() == 1) {
                        imageslider.setCurrentItem(2);
                    }else{
                        imageslider.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cont_us) {

            Intent i=new Intent(MainActivity.this,ContactUsPage.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.registration) {
            Intent i=new Intent(MainActivity.this,RegistrationPage.class);
            startActivity(i);

        } else if (id == R.id.abt_aux) {
            Intent i=new Intent(MainActivity.this,AboutPage.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = "http://play.google.com/store/apps/details?id=auxesisapp.anjanproduction.com.auxesis";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
            return true;
        }
        else if(id==R.id.notification){
            Intent i=new Intent(MainActivity.this,Notification.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public class SectionPageAdapter extends FragmentPagerAdapter{


        public SectionPageAdapter(FragmentManager fm){
            super(fm);

        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new Home();

                case 1:
                    return new Community();

                case 2:
                default:
                    return new Favourites();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "";
                case 1:

                    return "";

                case 2:
                    default:
                        return "";

            }
        }
    }
    public void saveTasksToSP(Context context, ArrayList<notoficationModel> fav){

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(fav);
        editor.putString("NOT",json);
        editor.commit();

    }
    public ArrayList<notoficationModel> getTasksFromSP(Context context){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson=new Gson();
        String json=preferences.getString("NOT","");
        list=gson.fromJson(json,new TypeToken<ArrayList<notoficationModel>>(){}.getType());
        return list;
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Reading permission is requied for this app. Otherwise the app may not function properly.Grant permission?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
            Button buttonbackground1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonbackground1.setBackgroundColor(Color.BLUE);
            Button buttonbackground = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            buttonbackground.setBackgroundColor(Color.BLUE);

        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Location permission is requied for this app. Otherwise the app may not function properly.Grant permission?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
            Button buttonbackground1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonbackground1.setBackgroundColor(Color.BLUE);
            Button buttonbackground = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            buttonbackground.setBackgroundColor(Color.BLUE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==MY_PERMISSIONS_REQUEST_LOCATION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              //  Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==MY_PERMISSION_REQUEST_WRITE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
               Toast.makeText(this, "Permission DENIED. The app may not work properly", Toast.LENGTH_SHORT).show();
            }
        }
    }

    }

