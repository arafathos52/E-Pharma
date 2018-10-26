package com.example.maruf.e_pharma.Trends.Calories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

public class Calorie_Result extends AppCompatActivity {

    TextView txtCategory;
    TextView txtCalories;
    TextView txtComment;
    Button btnBack;

    String body_status ="", calorie ="",comment="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie__result);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //init view
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        txtCalories = (TextView) findViewById(R.id.txtCalories);
        txtComment = (TextView) findViewById(R.id.comment);
        btnBack = (Button) findViewById(R.id.btnBack);


       //get  data
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            body_status = extras.getString("Body_Status");
            calorie = extras.getString("Nedd_Calorie");
        }

        if (body_status.equals("Thin")){
            body_status = "THIN";
            comment="YOU SHOULD EAT HEALTHY FOOD";
            txtCategory.setTextColor(getResources().getColor(R.color.thin));
        }else if (body_status.equals("FAT")){
            body_status = "FAT";
            comment="YOU SHOULD EXERCISE REGULARLY";
            txtCategory.setTextColor(getResources().getColor(R.color.fat));
        }else{
            body_status = "NORMAL";
            comment="KEEP SMILING!! YOU ARE FIT.";
            txtCategory.setTextColor(getResources().getColor(R.color.normal));
        }

        txtCategory.setText(body_status);
        txtCalories.setText(calorie);
        txtComment.setText(comment);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

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
