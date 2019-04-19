package auxesisapp.anjanproduction.com.auxesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Comments extends AppCompatActivity {
        private String name,post,postId,downloadUrl,commentId;
        private ImageView userImage;
        private Button commntBtn;
        private TextView nameText;
        private TextInputLayout textInputLayout;
        private TextView postText;
        private ListView listView;
        private DatabaseReference mReference;
        private FirebaseUser mUser;
        private Query query;
        private ArrayList<recycler_Post_Model> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Comments.this,Social.class));
            }
        });
        mUser=FirebaseAuth.getInstance().getCurrentUser();
        mReference= FirebaseDatabase.getInstance().getReference("comments");

        initValues();
        initListView();

        commntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                commentId=mReference.push().getKey();
                checkAndExecute();


            }
        });



    }

    private void initListView() {

        query=mReference.orderByChild("postId").equalTo(postId);
       query.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               list=new ArrayList<>();
               for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                   recycler_Post_Model model=snapshot.getValue(recycler_Post_Model.class);
                   recycler_Post_Model newModel=new recycler_Post_Model(model.getName(),model.getPost(),model.getPhotoUrl(),model.getPostId(),model.getDateAndTime());
                   list.add(newModel);


               }
               listView_Adapter adapter=new listView_Adapter(Comments.this,list);
               listView.setAdapter(adapter);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
               Toast.makeText(Comments.this,"Error loding comments",Toast.LENGTH_SHORT).show();

           }
       });


    }

    private void checkAndExecute() {
        String comment=textInputLayout.getEditText().getText().toString();
        if(comment.isEmpty()){
            textInputLayout.getEditText().setError("Add a comment first");
            textInputLayout.getEditText().requestFocus();
            return;
        }
        recycler_Post_Model model=new recycler_Post_Model(mUser.getDisplayName(),comment,mUser.getPhotoUrl().toString(),postId,"0");
        mReference.child(commentId).setValue(model);
    }

    private void initValues() {

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        post=extras.getString("post");
        downloadUrl=extras.getString("downloadUrl");
        postId=extras.getString("postId");
        textInputLayout=(TextInputLayout)findViewById(R.id.commentText);
        userImage=(ImageView)findViewById(R.id.userImage_comments);
        commntBtn=(Button)findViewById(R.id.comment);
        postText=(TextView)findViewById(R.id.postText_comments);
        nameText=(TextView)findViewById(R.id.nameText_comments);
        postText.setText(post);
        nameText.setText(name);
        Picasso.with(this).load(downloadUrl).placeholder(R.drawable.gender).into(userImage);
        listView=(ListView)findViewById(R.id.commentlist);
        listView.setEmptyView(findViewById(R.id.emptyList));

    }

}
