package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.sql.Ref;

import android.os.Bundle;

public class AdminAddMostWanted extends AppCompatActivity {
    EditText e1,e2;
    StorageReference mStorage;
    DatabaseReference mRef;
    ImageView imgview;
    Uri FilePathUri;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_most_wanted);
        e1 = (EditText)findViewById(R.id.mostname);
        e2 = (EditText)findViewById(R.id.mostcrime);
        imgview = (ImageView)findViewById(R.id.image_view);
        mStorage = FirebaseStorage.getInstance().getReference("mostwantedlist");
        mRef = FirebaseDatabase.getInstance().getReference("mostwantedlist");
        progressDialog = new ProgressDialog(AdminAddMostWanted.this);
    }

    public void browse(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), Image_Request_Code);
    }

    public void upload(View view) {
        String name = e1.getText().toString();
        String crime = e2.getText().toString();
        if(!name.isEmpty() && !crime.isEmpty()){
            UploadImage();
        }
        else{
            Toast.makeText(this, "Enter Details of person", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null){

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = mStorage.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloaduri = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                    DatabaseReference newPost = mRef.push();
                                    String fname = e1.getText().toString().trim();
                                    String fcrime = e2.getText().toString();
                                    MostWantedClass imageUploadInfo = new MostWantedClass(fname,fcrime,task.getResult().toString());
                                    String ImageUploadId = mRef.push().getKey();
                                    mRef.child(ImageUploadId).setValue(imageUploadInfo);
                                    e1.setText("");
                                    e2.setText("");
                                    Intent uploaded = new Intent(AdminAddMostWanted.this,AdminDashboard.class);
                                    startActivity(uploaded);
                                }
                            });
//                            @SuppressWarnings("VisibleForTests")
//                            MostWantedClass imageUploadInfo = new MostWantedClass(TempImageName,CrimeName,taskSnapshot.getUploadSessionUri().toString());
//                            String ImageUploadId = databaseReference.push().getKey();
//                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
//
//
                        }
                    });
        }
        else {

            Toast.makeText(AdminAddMostWanted.this, "Please Select Image", Toast.LENGTH_LONG).show();

        }
    }



}