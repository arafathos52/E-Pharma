package com.example.maruf.e_pharma.User_Profile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuvo.medicare.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends Activity {

    private DatabaseReference mDatabase, status;
    private FirebaseAuth mAuth;
    TextView weight,height,age,name,gmail,mobile,gender;
    String weight_value;
    Button close;
    String local_height;
    float bmi_value;
    ImageView profile_pic;
    //for pie chart
    private RelativeLayout mainLayout;
    private PieChart mChart;
    private FrameLayout chartContainer;
    private float[] yData = new float[2];
    private String[] xData = {"Weight","BMI"};
    private FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        weight = (TextView)findViewById(R.id.profile_weight);
        age = (TextView)findViewById(R.id.profile_age);
        height = (TextView)findViewById(R.id.profile_height);
        gmail = (TextView)findViewById(R.id.profile_gmail);
        name = (TextView)findViewById(R.id.profile_name);
        mobile = (TextView)findViewById(R.id.profile_mob);
        gender = (TextView)findViewById(R.id.profile_gender);
        close = (Button) findViewById(R.id.close);
        getProfileWeight();
        weight.setText(weight_value);

        //close button action
        FinishActivity();

        //show profile data
        show_profile_data();


        //update piechartdata
        yData[0]=(float)Integer.parseInt(weight_value);




    }

    public void getProfileWeight()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        weight_value = prefs.getString("weight_val", "65"); //no id: default value
    }

    public void FinishActivity()
    {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    void show_profile_data()
    {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Profile").child(userId);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profile_data temp = new profile_data();
                temp= dataSnapshot.getValue(profile_data.class);
                height.setText(temp.getHeight());
                local_height=temp.getHeight();
                age.setText(temp.getAge());
                name.setText(temp.getName());
                gmail.setText(temp.getGmail());
                mobile.setText(temp.getMobile());
                gender.setText(temp.getGender());
                if(temp.getGender()=="Female"||temp.getGender()=="FEMALE"||temp.getGender()=="Female")
                {
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.pro_female);
                    profile_pic.setImageBitmap(bitmap);
                }
                calculate_bmi(weight_value,local_height);
                //show chart
                yData[1]=bmi_value;
                PieChart();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void PieChart()
    {
        mainLayout = (RelativeLayout) findViewById(R.id.chartMain);
        chartContainer =(FrameLayout) findViewById(R.id.chartContainer);

        mChart = new PieChart(this);
        //add pie chart to main layout
        //mainLayout.addView(mChart);
        chartContainer.addView(mChart);
        //mainLayout.setBackgroundColor(Color.LTGRAY);
        //  mainLayout.setBackgroundColor(Color.parseColor("#55656C"));
        mainLayout.setBackgroundColor(Color.WHITE);

        //configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Your Health Status");

        //enable hole and configure
        mChart.setDrawHoleEnabled(true);
        // mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(40);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotation(0);
        mChart.setRotationEnabled(true);

        //set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display message when value selected
                if (e == null)
                    return;

                Toast.makeText(Profile.this, xData[e.getXIndex()]+" = "+e.getVal()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        // add Data
        addData();

        //customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }


    //this function add data to chart
    private void addData(){
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i=0; i< yData.length; i++) {
            yVals1.add(new Entry(yData[i], i));
        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i=0; i< xData.length; i++) {
            xVals.add(xData[i]);
        }
        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        for (int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);


        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        //instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
       // data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);

        mChart.setData(data);

        //undo all higlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }


    void calculate_bmi(String weight,String height)
    {
        double weight_val = Double.parseDouble(weight);
        double height_val = Double.parseDouble(height);


        double meter = (height_val*12)*0.0254;
        double bmi = (weight_val/(meter*meter))+1;
        bmi_value= (float) bmi;
        Log.d("BMI",Double.toString(bmi+1));

    }


}


