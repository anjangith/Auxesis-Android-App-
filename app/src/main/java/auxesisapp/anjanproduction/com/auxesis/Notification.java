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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    private ListView listView;
    private ArrayList<notoficationModel> list;
    public static NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("NOTIFICATION");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     finish();
                                                 }

                                             });
        listView=(ListView)findViewById(R.id.notilist);
        list=getTasksFromSP(Notification.this);
        if(list==null){
            list=new ArrayList<>();

        }

        adapter=new NotificationAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptyNotifi));
    }


    public void saveTasksToSP(Context context, ArrayList<detailsModel> fav){

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
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

}
