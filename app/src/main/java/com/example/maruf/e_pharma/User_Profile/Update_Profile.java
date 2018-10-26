
package com.example.maruf.e_pharma.User_Profile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shuvo.medicare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_Profile extends AppCompatActivity {

    private EditText mNameField,mEmailField,mGender,mAge,mHeight,mMobile;
    private Button mUpdate,back;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__profile);

        mNameField = (EditText) findViewById(R.id.input_name);
        mEmailField = (EditText) findViewById(R.id.input_email);
        mAge = (EditText) findViewById(R.id.input_age);
        mHeight = (EditText) findViewById(R.id.input_height);
        mMobile = (EditText) findViewById(R.id.input_mobile);
        mGender= (EditText) findViewById(R.id.input_sex);
        mUpdate= (Button) findViewById(R.id.btn_update);


        mUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       profile_updater();

                    }
                }
        );

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }


    //for update profile data
    void profile_updater()
    {
        final String name = mNameField.getText().toString().trim();
        final String gmail = mEmailField.getText().toString().trim();
        final String age = mAge.getText().toString().trim();
        final String height = mHeight.getText().toString().trim();
        final String mobile = mMobile.getText().toString().trim();
        final String gender = mGender.getText().toString().trim();


        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(gmail)&& !TextUtils.isEmpty(age)&& !TextUtils.isEmpty(height)&& !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(gender)) {

            final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are You Sure ?");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Profile").child(userId);

                            if(profile_data.tEventID != null || profile_data.tEventID != "")
                            {
                                profile_data.tEventID = mDatabase.push().getKey();
                            }
                            mAuth = FirebaseAuth.getInstance();


                            profile_data profileData = new profile_data(name,gmail,age,height,mobile,gender,profile_data.tEventID,profile_data.tOpenerID);

                            mDatabase.setValue(profileData);
                            finish();

                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else {

            if (TextUtils.isEmpty(name))
                mNameField.setError("This field can not be blank");
            if (TextUtils.isEmpty(gmail))
                mEmailField.setError("This field can not be blank");
            if (TextUtils.isEmpty(age))
                mAge.setError("This field can not be blank");
            if (TextUtils.isEmpty(height))
                mHeight.setError("This field can not be blank");
            if (TextUtils.isEmpty(mobile))
                mMobile.setError("This field can not be blank");
            if (TextUtils.isEmpty(gender))
                mMobile.setError("This field can not be blank");

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
