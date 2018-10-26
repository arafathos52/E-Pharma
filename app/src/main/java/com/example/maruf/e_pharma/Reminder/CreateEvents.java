package com.example.maruf.e_pharma.Reminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shuvo.medicare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateEvents extends AppCompatActivity {

    public static int index=0;
    final Calendar calendar = Calendar.getInstance();
    public  String caller,ID;
    public int hour,month,minute,year,day;
    String choosedHour,choosedMinute,choosedTimeZone;
    int mYear,mMonth,mDay;
    boolean creatButton = false;
    int request_Code = 1 ;
    ArrayList<String> attr = new ArrayList<>();
    private EditText mName, mDate, mTime;
    private Context context;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText mDescription;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button mCreat,addReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);
        mName = (EditText) findViewById(R.id.editText8);
        mDate = (EditText) findViewById(R.id.editText11);
        mTime = (EditText) findViewById(R.id.editText12);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_creat_events);
        mDescription = (EditText) findViewById(R.id.editText6);
        addReminder = (Button) findViewById(R.id.addReminder);

        mDescription.setCursorVisible(true);
        mDescription.setFocusableInTouchMode(true);
        mDescription.requestFocus();
        mDescription.setEnabled(true);
        mDescription.setMovementMethod(new ScrollingMovementMethod());

        mName.setText(EventDetails.tName);
        mTime.setText(EventDetails.tTime);
        mDate.setText(EventDetails.tDate);
        mDescription.setText(EventDetails.tDescription);

        //back button

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //get Extra from caller
        Bundle extras = getIntent().getExtras();
        caller= extras.getString("caller");
        ID = extras.getString("ID");;

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR); // current year
                mMonth = c.get(Calendar.MONTH); // current month
                mDay = c.get(Calendar.DAY_OF_MONTH); // current day


                DatePickerDialog dialog = new DatePickerDialog(
                        CreateEvents.this,
                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear, mMonth, mDay);
              //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Calendar mcurrentTime = Calendar.getInstance();

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateEvents.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                       if (android.os.Build.VERSION.SDK_INT >= 23) {
                            time_setter(timePicker.getHour(), timePicker.getMinute());
                        }
                        else{
                            time_setter(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                        }



                        choosedHour = "";
                        choosedMinute = "";
                        choosedTimeZone = "";
                        if(selectedHour > 12){
                            choosedTimeZone = "PM";
                            selectedHour = selectedHour - 12;
                            if(selectedHour < 10){
                                choosedHour = "0"+selectedHour;
                            }else{
                                choosedHour = ""+selectedHour;
                            }

                        }else{
                            choosedTimeZone = "AM";
                            if(selectedHour < 10){
                                choosedHour = "0"+selectedHour;
                            }else{
                                choosedHour = ""+selectedHour;
                            }
                        }
                       // calendar.setTimeZone(TimeZone.getTimeZone(choosedTimeZone));

                        if(selectedMinute < 10){
                            choosedMinute = "0"+selectedMinute;
                        }else{
                            choosedMinute= ""+selectedMinute;
                        }


                        mTime.setText(choosedHour + ":" + choosedMinute +" "+choosedTimeZone);
                    }
                }, hour, minute, false);//Yes 24 hour time
                // mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });





        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date_setter(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
                month++;
                String date = day + "/" + month + "/" + year;
                mDate.setText(date);
            }
        };
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText mName, mDate, mTime;
                final TextView mDescription;
                mName = (EditText) findViewById(R.id.editText8);
                mDate = (EditText) findViewById(R.id.editText11);
                mTime = (EditText) findViewById(R.id.editText12);
                mDescription = (TextView) findViewById(R.id.editText6);


                final String name = mName.getText().toString();
                final String date = mDate.getText().toString();
                final String time = mTime.getText().toString();

                final String description = mDescription.getText().toString();

                //Changeing Here
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(description)  && creatButton == false) {
                    mDate.setError(null);
                    mName.setError(null);
                    mDescription.setError(null);
                    mTime.setError(null);


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateEvents.this);
                    builder1.setMessage("Are You Sure ?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

                                    if(EventDetails.tEventID != null || EventDetails.tEventID != "")
                                    {
                                        EventDetails.tEventID = mDatabase.push().getKey();
                                    }
                                    mAuth = FirebaseAuth.getInstance();
                                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                    final String openerID = mAuth.getCurrentUser().getUid();

                                    EventDetails event = new EventDetails(name, date, time, description, EventDetails.tEventID,openerID);

                                    mDatabase.child(userId).child(EventDetails.tEventID).setValue(event);

                                    creatButton = true;
                                    dialog.cancel();
                                    create_alarm();
                                    setAlarm(calendar.getTimeInMillis(),index++);
                                    Log.d("Uid_",userId);
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
                        mName.setError("This field can not be blank");
                    if (TextUtils.isEmpty(date))
                        mDate.setError("This field can not be blank");
                    if (TextUtils.isEmpty(time))
                        mTime.setError("This field can not be blank");
                    if (TextUtils.isEmpty(description))
                        mDescription.setError("This field can not be blank");

                }

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

    @Override
    protected void onRestart() {
        super.onRestart();

    }
    private void setAlarm(long time,int index) {
       // MyAlarm []myAlarm = new MyAlarm[1000];
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyAlarm.class);
        Log.d("index",Integer.toString(index));
        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, index, i, 0);
        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }

    public void create_alarm()
    {

        calendar.set(year, month, day, hour, minute, 0);

    }
    public void date_setter(int y,int m,int d)
    {
        this.year = y;
        this.month = m;
        this.day = d ;
    }
    public void time_setter(int h,int mint)
    {
        this.hour = h;
        this.minute = mint;
    }





}


