package auxesisapp.anjanproduction.com.auxesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Add_Post extends AppCompatActivity {
    private TextInputLayout textInputLayout;
    private Button postBtn;
    private DatabaseReference mRefrence;
    private FirebaseUser mUser;
    String postId;
    String name;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add a new post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Add_Post.this,Social.class));
            }
        });
        progressBar=(ProgressBar)findViewById(R.id.postPbar);
        textInputLayout = (TextInputLayout) findViewById(R.id.post);
        postBtn = (Button) findViewById(R.id.postbtn);
        mRefrence= FirebaseDatabase.getInstance().getReference("posts");
        mUser= FirebaseAuth.getInstance().getCurrentUser();
        postId=mRefrence.push().getKey();

        name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAndUpload();

            }
        });


    }





    private void checkAndUpload() {
        {
            String postText = textInputLayout.getEditText().getText().toString();
            if (postText.isEmpty()) {
                textInputLayout.getEditText().requestFocus();
                textInputLayout.getEditText().setError("Enter a valid post before uploading");
                return;
            }else{
                pustToDataBase();
            }




        }

    }

    private void pustToDataBase() {
        progressBar.setVisibility(View.VISIBLE);

        postModel model=new postModel(mUser.getDisplayName(),textInputLayout.getEditText().getText().toString(),mUser.getUid(),postId,mUser.getPhotoUrl().toString(), DateFormat.getDateTimeInstance().format(new Date()));
        mRefrence.child(postId).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressBar.setVisibility(View.GONE);
                finish();
                startActivity(new Intent(Add_Post.this,Social.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Add_Post.this,e.getMessage(),Toast.LENGTH_SHORT);
            }
        });


    }
}
