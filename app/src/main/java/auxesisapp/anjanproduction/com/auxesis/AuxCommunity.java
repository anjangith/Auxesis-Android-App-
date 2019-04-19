package auxesisapp.anjanproduction.com.auxesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuxCommunity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private TextView signUpText;
    private Button signinBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(AuxCommunity.this,Social.class));
                    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxcommunity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();
        emailText=(EditText)findViewById(R.id.email);
        passwordText=(EditText)findViewById(R.id.password);
        signinBtn=(Button)findViewById(R.id.loginbtn);
        progressBar=(ProgressBar)findViewById(R.id.progess);
        signUpText=(TextView)findViewById(R.id.signuptext);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AuxCommunity.this,Registration.class);
                startActivity(i);
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signInUser();
            }
        });


    }

    private void signInUser() {



        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (email.isEmpty()){
            emailText.setError("Please enter your email");
            emailText.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please enter a valid email");
            emailText.requestFocus();
            return;

        }
        if(password.isEmpty()){
            passwordText.setError("Please enter password");
            passwordText.requestFocus();
            return;
        }

        if(passwordText.length()<6){
            passwordText.setError("Please enter a valid password");
            passwordText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AuxCommunity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                                finish();
                                progressBar.setVisibility(View.GONE);
                                Intent i=new Intent(AuxCommunity.this,Social.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);

                    }
                    else{
                        Toast.makeText(AuxCommunity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
            }
        });

    }

}
