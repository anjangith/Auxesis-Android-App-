package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 24-06-2018.
 */

public class CategoriesAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<CategoriesModel> models;


    public CategoriesAdapter(Context context,ArrayList<CategoriesModel> models){
        this.context=context;
        this.models=models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false);

        }

        final CategoriesModel model=(CategoriesModel)this.getItem(i);
        TextView title=(TextView)view.findViewById(R.id.name);

        ImageView imageView=(ImageView)view.findViewById(R.id.imageView6);
        title.setText(model.getEventName());
        imageView.setImageResource(model.getImageLogo());


        return view;
    }


}
