package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class FavItems extends AppCompatActivity {
    private ListView lv;
    private ArrayList<detailsModel> list = new ArrayList<>();
    public static  favouritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("FAVOURITES");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv=(ListView)findViewById(R.id.favlistaaa);
        list=getTasksFromSP(this);
        if(list==null){
            list=new ArrayList<>();
        }
        adapter=new favouritesAdapter(this,list);
        lv.setAdapter(adapter);
        lv.setEmptyView(findViewById(R.id.emptxt));


    }

    public ArrayList<detailsModel> getTasksFromSP(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson=new Gson();
        String json=sharedPreferences.getString("favourites","");
        list=gson.fromJson(json,new TypeToken<ArrayList<detailsModel>>(){}.getType());
        return list;
    }

}
