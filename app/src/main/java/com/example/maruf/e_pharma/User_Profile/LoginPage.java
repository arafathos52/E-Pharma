package com.example.maruf.e_pharma.User_Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuvo.medicare.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText username;
    private EditText pass;
    private Button login,signUp,updateProfile;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN =1;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        mAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        signUp = (Button) findViewById(R.id.signUp);
        updateProfile = (Button) findViewById(R.id.updateProfile);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mAuthListener= new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() !=null)
                {
                   // startActivity(new Intent(LoginPage.this,test.class));
                }
                else
                {
                   // Toast.makeText(LoginPage.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }

        };
        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent = new Intent(LoginPage.this,sign_up_activity.class);
                        startActivity(intent);

                    }
                }
        );
        updateProfile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(FirebaseAuth.getInstance().getCurrentUser()==null)
                        {
                            Toast.makeText(LoginPage.this, "Please login first", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(LoginPage.this,Update_Profile.class);
                            startActivity(intent);
                        }

                    }
                }
        );
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sign();
                    }
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    void sign()
    {
        String email = username.getText().toString();
        String password = pass.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginPage.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                   finish();
            }
        });
    }




}
