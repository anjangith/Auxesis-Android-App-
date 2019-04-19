package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by HP on 05-07-2018.
 */

public class listView_Adapter extends BaseAdapter {
    private ArrayList<recycler_Post_Model> list;
    private Context context;


    public listView_Adapter(Context c,ArrayList<recycler_Post_Model> list){

        this.list=list;
        this.context=c;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.comment_view, viewGroup, false);
        }
        ImageView imageView=(ImageView)view.findViewById(R.id.comment_imageView);
        TextView name=(TextView)view.findViewById(R.id.comment_nameView);
        TextView comments=(TextView)view.findViewById(R.id.comment_comment);
        recycler_Post_Model model=(recycler_Post_Model)this.getItem(i);
        Picasso.with(context).load(model.getPhotoUrl()).placeholder(R.drawable.gender).into(imageView);
        name.setText(model.getName());
        comments.setText(model.getPost());
        return view;
    }
}
