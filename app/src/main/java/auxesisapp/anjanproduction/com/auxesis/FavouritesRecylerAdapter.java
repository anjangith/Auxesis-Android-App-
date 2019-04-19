package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavouritesRecylerAdapter extends RecyclerView.Adapter<FavouritesRecylerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<detailsModel> list;

    public FavouritesRecylerAdapter(Context context, ArrayList<detailsModel> list){

        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.favouritemodel,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        detailsModel model=list.get(position);
        holder.nameText.setText(model.getName());
        holder.descText.setText(null);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameText;
        TextView descText;
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            nameText=(TextView)itemView.findViewById(R.id.namedetails1);
            descText=(TextView) itemView.findViewById(R.id.descriptiondetails1);
            button=(Button)itemView.findViewById(R.id.deletbtn);

        }
    }

}


