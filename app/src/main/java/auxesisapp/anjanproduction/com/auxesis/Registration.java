package auxesisapp.anjanproduction.com.auxesis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaCodec;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registration extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signupBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        email=(EditText)findViewById(R.id.emailRegister);
        password=(EditText)findViewById(R.id.passwordregister);
        setSupportActionBar(toolbar);
        signupBtn=(Button)findViewById(R.id.signupbtn);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);


        mAuth=FirebaseAuth.getInstance();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();
            }
        });
    }

    private void registerUser() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        if (emailText.isEmpty()){
            email.setError("Please enter your email");
            email.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            password.setError("Please enter a valid email");
            password.requestFocus();
            return;

        }
        if(passwordText.isEmpty()){
            password.setError("Please enter password");
            password.requestFocus();
            return;
        }

        if(passwordText.length()<6){
            password.setError("Please enter a valid password");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(Registration.this,"Registered",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(Registration.this,PostActivity.class));

                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(Registration.this,"You have already registered",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Registration.this,"Error",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

}
