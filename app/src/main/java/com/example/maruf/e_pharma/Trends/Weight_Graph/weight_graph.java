package com.example.maruf.e_pharma.Trends.Weight_Graph;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.shuvo.medicare.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class weight_graph extends Fragment {
    public EditText date,weight;
    int mYear,mMonth,mDay;
    public float weight_value;
    Button update;
    public String date1;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    View view;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public String weigh_pro;
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<String>();
    LineData data;
    LineDataSet dataset;
    public static int pos=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weight_graph, container,false);
        super.onCreate(savedInstanceState);
        date = (EditText) view.findViewById(R.id.weight_date);
        weight = (EditText) view.findViewById(R.id.weight);
        update = (Button) view.findViewById(R.id.update_weight);


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Uid_",userId);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Weight").child(userId);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entries.clear();
                pos=0;
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    WeightData temp = new WeightData();
                    temp = users.getValue(WeightData.class);
                    String key = dataSnapshot.getKey();


                    if (!temp.getWeight().equals("") ) {
                        addData(temp,pos);
                        Log.d("popcon",temp.getWeight());
                        pos++;
                    }

                }
                Log.d("popcon","end");
                show_graph();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR); // current year
                mMonth = c.get(Calendar.MONTH); // current month
                mDay = c.get(Calendar.DAY_OF_MONTH); // current day


                DatePickerDialog dialog = new DatePickerDialog(
                        view.getContext(),
                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear, mMonth, mDay);
                dialog.show();
            }
        });
        weight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date1 = year+ "/" + month + "/" + day;
                date.setText(date1);


            }
        };
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update_helper();
                    }
                }
        );
        return view;
    }

    //show dialog to entry weight
    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        View promptView = layoutInflater.inflate(R.layout.input_weight_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.weight_log);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        weight.setText( editText.getText());
                        weight_value=(float) Integer.parseInt(editText.getText().toString());
                        setProfileWeight(editText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void addData(WeightData temp,int pos)
    {
        float val = (float)Integer.parseInt(temp.getWeight());
        entries.add(new Entry(val, pos));

        Log.d("posit2",Integer.toString(pos));
        labels.add(temp.getDate());
    }

    //show the graph
    public void show_graph()
    {
        LineChart lineChart = (LineChart) view.findViewById(R.id.chart_graph);

        Log.d("posit2","Called");

        dataset = new LineDataSet(entries, "# colors");


        data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);
        dataset.setDrawCubic(false);
        dataset.setDrawFilled(true);
        dataset.setFillColor(getResources().getColor(R.color.graph));
        dataset.setCircleRadius(3f);
        dataset.setValueTextSize(9f);

        lineChart.setData(data);
        lineChart.setDescription("Weight");
        lineChart.animateY(3000);
        lineChart.animateX(3000, Easing.EasingOption.EaseInOutQuart);


    }


    //Data passing function
    void update_helper()
    {
        final String dat = date.getText().toString();
        final String weg = weight.getText().toString();
        if (!TextUtils.isEmpty(dat) && !TextUtils.isEmpty(weg) ) {
            date.setText(null);
            weight.setText(null);


            AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getActivity());
            builder1.setMessage("Are You Sure ?");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Weight");
                            Log.d("basefire","holla");
                            if(WeightData.tEventID != null || WeightData.tEventID != "")
                            {
                                WeightData.tEventID = mDatabase.push().getKey();
                            }
                            mAuth = FirebaseAuth.getInstance();
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Log.d("basefire",userId);
                            final String openerID = mAuth.getCurrentUser().getUid();
                            Log.d("basefire",openerID);
                            WeightData event = new WeightData(dat,weg,WeightData.tEventID,openerID);

                            mDatabase.child(userId).child(WeightData.tEventID).setValue(event);
                            data.clearValues();
                            labels.clear();
                            pos=0;
                            dialog.cancel();

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
    }
    public void setProfileWeight(String input)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("weight_val", input); //InputString: from the EditText
        editor.commit();
    }


}
