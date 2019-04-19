package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 05-08-2018.
 */

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CategoriesModel> list;


    public CategoriesRecyclerAdapter(Context context,ArrayList<CategoriesModel> recyclerPostModels){
        this.context=context;
        this.list=recyclerPostModels;

    }


    @Override
    public CategoriesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        CategoriesRecyclerAdapter.ViewHolder viewHolder=new CategoriesRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriesRecyclerAdapter.ViewHolder holder, int position) {


        CategoriesModel model=list.get(position);
        holder.name.setText(model.getEventName());
        holder.image.setImageResource(model.getImageLogo());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.imageView6);

        }
    }



}
