package com.example.maruf.e_pharma.Trends.Calories;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuvo.medicare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calorie_calculator extends Fragment {

    TextView txtWeight;
    TextView txtHeight;
    TextView txtAge;
    ImageView imgMale;
    ImageView imgFemale;
    ImageView imgLow;
    ImageView imgMedium;
    ImageView imgHigh;

    Button calculate;

    private int age=0,weight=0,height=0;
    private char gender=0;
    private String activity="";
    private String Body_Status="";
    private String neededCalorie="";
    public Calorie_calculator() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.calorie_calculator, container,false);
        //initial view
        txtWeight = (TextView) view.findViewById(R.id.WeightField);
        txtHeight = (TextView) view.findViewById(R.id.HeightField);
        txtAge = (TextView) view.findViewById(R.id.txtAge);
        imgMale = (ImageView) view.findViewById(R.id.male_image);
        imgFemale = (ImageView) view.findViewById(R.id.female_image);
        imgLow = (ImageView) view.findViewById(R.id.low);
        imgMedium = (ImageView) view.findViewById(R.id.medium_active);
        imgHigh = (ImageView) view.findViewById(R.id.img_high);

        calculate=(Button) view.findViewById(R.id.calculate);

        //check Male
        imgMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgMale.setColorFilter(getResources().getColor(R.color.tab1));
                imgFemale.clearColorFilter();
                gender = 'M';
            }
        });

        //check female
        imgFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgFemale.setColorFilter(getResources().getColor(R.color.tab1));
                imgMale.clearColorFilter();
                gender = 'F';
            }
        });


        imgLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLow.setColorFilter(getResources().getColor(R.color.tab1));
                imgMedium.clearColorFilter();;
                imgHigh.clearColorFilter();
                activity="Low";
            }
        });


        imgMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgMedium.setColorFilter(getResources().getColor(R.color.tab1));
                imgLow.clearColorFilter();;
                imgHigh.clearColorFilter();
                activity="Medium";
            }
        });


        imgHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgHigh.setColorFilter(getResources().getColor(R.color.tab1));
                imgMedium.clearColorFilter();;
                imgLow.clearColorFilter();
                activity="High";
            }
        });

        //calculate button action
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()){
                    Calculate_Calorie cc = new Calculate_Calorie(weight,height,age,gender,activity);
                    Body_Status=cc.getBodyCategory();
                    neededCalorie=cc.getCalorieNeed()+" Cal";
                    Log.d("Calorie",neededCalorie);
                }else{

                    Toast.makeText(getActivity(), "Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //throws Result ke activity Result
                Intent intent = new Intent(view.getContext(), Calorie_Result.class);
                Bundle extras = new Bundle();
                extras.putString("Body_Status",Body_Status);
                extras.putString("Nedd_Calorie",neededCalorie);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        return view;
    }

    private boolean valid(){
        if (txtAge.getText().toString().equals("")||
                txtWeight.getText().toString().equals("")||
                txtHeight.getText().toString().equals("")||
                gender==0||activity.equals("")
                ){
            return false;
        }

        try {
            weight = Integer.parseInt(txtWeight.getText().toString());
            height = Integer.parseInt(txtHeight.getText().toString());
            age = Integer.parseInt(txtAge.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void setProfileWeight(String input)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("weight_val", input); //InputString: from the EditText
        editor.commit();
    }


}
