package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.crimemanagement.ChooseComplaintArea.complaintAreaForStoringInDatabase;
import static com.example.crimemanagement.UserLoggedIn.username;

public class FileComplaint extends AppCompatActivity {
    EditText crimearea,appCompatEditText;
    StorageReference mStorage;
    DatabaseReference mRefComplaint;
    DatabaseReference mRefUserDetails;
    String usernameuser;
    ImageView imgview;
    String databaserefname;
    String aadhaaruser;
    String typecomplaint;
    Uri FilePathUri;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_complaint);
        crimearea = (EditText)findViewById(R.id.crimeArea);
        appCompatEditText = (EditText)findViewById(R.id.descriptionmultiple);
        usernameuser = username;
        databaserefname = "centralzone"; // if null
        databaserefname = complaintAreaForStoringInDatabase;
        System.out.println("Reference name : "+databaserefname);
        Spinner mySpinner = findViewById(R.id.complaintType);
        imgview = (ImageView)findViewById(R.id.image_view1);
        mStorage = FirebaseStorage.getInstance().getReference("complaints");

        // area wise storing : complaint area
        mRefComplaint = FirebaseDatabase.getInstance().getReference(databaserefname);

        mRefUserDetails = FirebaseDatabase.getInstance().getReference("users");

        // retriving aadhaar number for storing in firebase

        mRefUserDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                aadhaaruser = snapshot.child(usernameuser).child("aadhaarnumber").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        progressDialog = new ProgressDialog(FileComplaint.this);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(FileComplaint.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        // create array in Strings.xml and add it to here
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                typecomplaint = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void choosemultiple(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), Image_Request_Code);
    }

    public void uploadmultiple(View view) {
        String crimeloc = crimearea.getText().toString();
        String des = appCompatEditText.getText().toString();
        String comptype = typecomplaint;
        if(!crimeloc.isEmpty() && !des.isEmpty() && !comptype.isEmpty()){
            UploadImage();
        }
        else{
            Toast.makeText(this, "Enter Complete Details", Toast.LENGTH_LONG).show();
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
                                    DatabaseReference newPost = mRefComplaint.push();
                                    String crimeloc = crimearea.getText().toString().trim();
                                    String desc = appCompatEditText.getText().toString();
                                    String type = typecomplaint;
                                    ComplaintClass imageUploadInfo = new ComplaintClass(task.getResult().toString(),crimeloc,type,desc,usernameuser,aadhaaruser,"pending");
                                    String ImageUploadId = mRefComplaint.push().getKey();
                                    mRefComplaint.child(ImageUploadId).setValue(imageUploadInfo);
                                    mRefComplaint.child(ImageUploadId).child("date").setValue(new Date().toString());
                                    mRefComplaint.child(ImageUploadId).child("id").setValue(ImageUploadId);

                                    // adding data to the user : compliant section to show the status complaint
                                    mRefUserDetails.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            mRefUserDetails.child(usernameuser).child("complaintslist").child(ImageUploadId).child("status").setValue("pending");
                                            mRefUserDetails.child(usernameuser).child("complaintslist").child(ImageUploadId).child("date").setValue(new Date().toString());
                                            mRefUserDetails.child(usernameuser).child("complaintslist").child(ImageUploadId).child("type").setValue(type);
                                            mRefUserDetails.child(usernameuser).child("complaintslist").child(ImageUploadId).child("zone").setValue(databaserefname);
                                            mRefUserDetails.child(usernameuser).child("complaintslist").child(ImageUploadId).child("complaintid").setValue(ImageUploadId);
                                            mRefUserDetails.child(usernameuser).child("complaintslist").child(ImageUploadId).child("imageURL").setValue(task.getResult().toString());
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    crimearea.setText("");
                                    appCompatEditText.setText("");
                                    Toast.makeText(getApplicationContext(), "Complaint Filed Successfully ", Toast.LENGTH_LONG).show();
                                }
                            });
//                            @SuppressWarnings("VisibleForTests")
//                            MostWantedClass imageUploadInfo = new MostWantedClass(TempImageName,CrimeName,taskSnapshot.getUploadSessionUri().toString());
//                            String ImageUploadId = databaseReference.push().getKey();
//                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                             Intent upload = new Intent(FileComplaint.this,UserLoggedIn.class);
                                    startActivity(upload);
//
                        }
                    });
        }
        else {

            Toast.makeText(FileComplaint.this, "Please Select Image", Toast.LENGTH_LONG).show();

        }
    }
}