package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by HP on 20-07-2018.
 */

public class favouritesAdapter extends BaseAdapter {

    private Context ctx;
    private ArrayList<detailsModel> list;
    private ArrayList<detailsModel> favourites=new ArrayList<>();


    public favouritesAdapter(Context context,ArrayList<detailsModel> list){
        this.ctx=context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(ctx).inflate(R.layout.favouritemodel,viewGroup,false);
        }
        final detailsModel model=(detailsModel)this.getItem(i);
        TextView nameText=(TextView)view.findViewById(R.id.namedetails1);
        TextView descText=(TextView)view.findViewById(R.id.descriptiondetails1);
        Button button=(Button)view.findViewById(R.id.deletbtn);
        nameText.setText(model.getName());
        descText.setText(model.getDescp());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                list.remove(i);
                saveTasksToSP(ctx,list);
                FavItems.adapter.notifyDataSetChanged();

                Toast.makeText(ctx, "Item Removed", Toast.LENGTH_SHORT).show();


            }


        });

        return view;
    }


    public void saveTasksToSP(Context context,ArrayList<detailsModel> fav){

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(fav);
        editor.putString("favourites",json);
        editor.commit();

    }
}
