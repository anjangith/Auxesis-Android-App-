package auxesisapp.anjanproduction.com.auxesis;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private ListView listView;
    private CategoriesRecyclerAdapter adapter;
    private ArrayList<CategoriesModel> models;
    public static int data;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root=inflater.inflate(R.layout.fragment_home,container,false);
       listView=(ListView)root.findViewById(R.id.lv_categ);
       CategoriesAdapter adapter=new CategoriesAdapter(getActivity(),initmodels());
       listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               data=i;
               Intent intent=new Intent(getActivity().getBaseContext(),CategoriesDescriptor.class);
               startActivity(intent);
           }
       });
        return root;

    }

    public int getData(){
        return data;
    }





    private ArrayList<CategoriesModel> initmodels() {

        ArrayList<CategoriesModel> models=new ArrayList<>();
        CategoriesModel m1=new CategoriesModel();
        m1.setEventName("SOCIAL INITIATIVE EVENTS");
        m1.setEventBit(R.mipmap.back1);
        m1.setImageLogo(R.drawable.baseline_local_florist_black_36dp);

        models.add(m1);

        m1=new CategoriesModel();
        m1.setEventName("TECHNICAL EVENTS");
        m1.setEventBit(R.mipmap.back2);
        m1.setImageLogo(R.drawable.baseline_bug_report_black_36dp);

        models.add(m1);


        m1=new CategoriesModel();
        m1.setEventName("CREATIVITY EVENTS");
        m1.setEventBit(R.mipmap.back3);
        m1.setImageLogo(R.drawable.baseline_wb_incandescent_black_36dp);

        models.add(m1);

        m1=new CategoriesModel();
        m1.setEventName("SPORTS EVENTS");
        m1.setEventBit(R.mipmap.back1);
        m1.setImageLogo(R.drawable.baseline_fitness_center_black_36dp);

        models.add(m1);

        m1=new CategoriesModel();
        m1.setEventName("SCHOOL AND MANAGEMENT");
        m1.setEventBit(R.mipmap.back2);
        m1.setImageLogo(R.drawable.baseline_whatshot_black_36dp);
        models.add(m1);

        m1=new CategoriesModel();
        m1.setEventName("CULTURAL EVENTS");
        m1.setEventBit(R.mipmap.back3);
        m1.setImageLogo(R.drawable.baseline_music_note_black_36dp);
        models.add(m1);

        return models;




    }

}
