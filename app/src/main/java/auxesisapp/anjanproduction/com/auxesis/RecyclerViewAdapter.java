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

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP on 02-07-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    List<recycler_Post_Model> list;


    public RecyclerViewAdapter(Context context,List<recycler_Post_Model> recyclerPostModels){
        this.context=context;
        this.list=recyclerPostModels;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.post_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        recycler_Post_Model model=list.get(position);
        holder.name.setText(model.getName());
        holder.post.setText(model.getPost());
         holder.time.setText(model.getDateAndTime());
        Picasso.with(context).load(model.getPhotoUrl()).placeholder(R.drawable.gender).into(holder.profile);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView post;
        public Button commnt;
        public ImageView profile;
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nameText);
            post=(TextView)itemView.findViewById(R.id.postText);
            commnt=(Button)itemView.findViewById(R.id.commentBtn);
            profile=(ImageView)itemView.findViewById(R.id.userImage);
            time=(TextView)itemView.findViewById(R.id.dateandtime);
            commnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context,Comments.class);
                    i.putExtra("name",list.get(getAdapterPosition()).getName());
                    i.putExtra("post",list.get(getAdapterPosition()).getPost());
                    i.putExtra("postId",list.get(getAdapterPosition()).getPostId());
                    i.putExtra("downloadUrl",list.get(getAdapterPosition()).getPhotoUrl());
                    context.startActivity(i);
                }
            });
        }
    }










}
