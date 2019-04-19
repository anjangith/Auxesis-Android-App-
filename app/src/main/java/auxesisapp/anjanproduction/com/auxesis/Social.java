package auxesisapp.anjanproduction.com.auxesis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Social extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    String name,url;

    private ArrayList<recycler_Post_Model> list = new ArrayList<>();
    private DatabaseReference mReference;
    private ProgressDialog progressDialog;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.social_menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.postRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Social.this));
        mReference = FirebaseDatabase.getInstance().getReference("posts");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        loadData();
        checkProfile();


    }

    private void loadData() {
        progressDialog.show();

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    postModel model = snap.getValue(postModel.class);
                    recycler_Post_Model newmodel = new recycler_Post_Model(model.getName(), model.getPost(),model.getDownloadUrl(), model.getPostId(),model.getTimeandDate());
                    list.add(newmodel);
                    Collections.reverse(list);
                    progressDialog.dismiss();

                }
                adapter = new RecyclerViewAdapter(Social.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Social.this, "Error loading data", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutitem:
                finish();
                mAuth.signOut();
                startActivity(new Intent(Social.this, AuxCommunity.class));
                return true;

            case R.id.addPostItem:
                finish();
                RecoverProf();
                return true;

            case R.id.profileItem:
                finish();
                startActivity(new Intent(Social.this, PostActivity.class));
                return true;


        }
        return super.onOptionsItemSelected(item);


    }

    private void checkProfile() {
        try {
             name = mAuth.getCurrentUser().getDisplayName().trim();
             url = mAuth.getCurrentUser().getPhotoUrl().toString().trim();
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void RecoverProf(){
        if(name.isEmpty()||url.isEmpty()){
            finish();
            Toast.makeText(this,"Update Your Profile",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,PostActivity.class);
            startActivity(i);

        }else{
            finish();
            startActivity(new Intent(Social.this, Add_Post.class));

        }
    }


}
