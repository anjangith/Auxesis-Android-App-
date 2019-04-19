package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class PostActivity extends AppCompatActivity {

    private static final int CHOOSE =101 ;
    private EditText userName;
    private Button saveBtn;
    private ImageView profilePic;
    private Uri imageUri;
    String downloadUrl;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private ProgressBar newProgressBar;


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(PostActivity.this,AuxCommunity.class));
        }

      //  if(mAuth.getCurrentUser().getDisplayName()!=null && mAuth.getCurrentUser().getPhotoUrl().toString()!=null){
            //finish();
          //  startActivity(new Intent(PostActivity.this,Social.class));
       // }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(PostActivity.this,Social.class));
            }
        });
        userName=(EditText)findViewById(R.id.username);
        saveBtn=(Button)findViewById(R.id.savebtn);
        profilePic=(ImageView)findViewById(R.id.cameraImage);
        progressBar=(ProgressBar)findViewById(R.id.pbar);
        newProgressBar=(ProgressBar)findViewById(R.id.newpb);
        mAuth=FirebaseAuth.getInstance();
        loadProfile();
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfo();


            }
        });


    }

    private void loadProfile() {
        try {

            String name = mAuth.getCurrentUser().getDisplayName();
            userName.setText(name);
            Picasso.with(this).load(mAuth.getCurrentUser().getPhotoUrl().toString()).placeholder(R.drawable.gender).into(profilePic);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void selectImage(){
        /*Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"SELECT PROFILE IMAGE"),CHOOSE);*/
        CropImage.activity(imageUri).start(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                    Bitmap finalBitmap=getResizedBitmap(bitmap,500);
                    profilePic.setImageBitmap(finalBitmap);
                    imageUri=getImageUri(this,finalBitmap);
                    uploadToStorage();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }
       /*  if(requestCode==CHOOSE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imageUri=data.getData();
           try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                profilePic.setImageBitmap(bitmap);
                uploadToStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }*/



        }


    private void uploadToStorage(){
        StorageReference mRef= FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
        newProgressBar.setVisibility(View.VISIBLE);
        if(imageUri!=null){
            mRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    newProgressBar.setVisibility(View.GONE);
                    downloadUrl=taskSnapshot.getDownloadUrl().toString();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(PostActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void saveInfo(){

        String name=userName.getText().toString().trim();
        if(name.isEmpty()){
            userName.setError("Enter a valid name");
            userName.requestFocus();
            return;
        }

        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null&&downloadUrl!=null){
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder().setDisplayName(name).setPhotoUri(imageUri).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    newProgressBar.setVisibility(View.GONE);
                    Toast.makeText(PostActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(PostActivity.this,Social.class));


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    newProgressBar.setVisibility(View.GONE);
                }
            });
        }else{
            Toast.makeText(PostActivity.this,"Update profile pic",Toast.LENGTH_SHORT).show();
            newProgressBar.setVisibility(View.GONE);
        }


    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
