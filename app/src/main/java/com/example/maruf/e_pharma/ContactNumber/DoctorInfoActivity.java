package com.example.maruf.e_pharma.ContactNumber;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shuvo.medicare.R;


public class DoctorInfoActivity extends AppCompatActivity {

    ContactsDatabaseAdapter.ContactsDatabaseHelper contactsDatabaseHelper;

    TextView doctorName,speciality,number,email,schedule,address;
    Button callDoctor;
    String doc_name;

    int UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_info);
        setSupportActionBar(myToolbar);

        doctorName=findViewById(R.id.doctor_name);
        speciality=findViewById(R.id.speciality);
        number=findViewById(R.id.phone_number);
        email=findViewById(R.id.email_text);
        schedule=findViewById(R.id.schedule);
        address=findViewById(R.id.address);
        callDoctor=findViewById(R.id.call_button);

       //int id=Integer.parseInt(getIntent().getStringExtra("id"));

        doc_name=getIntent().getStringExtra("d_name");

        //Log.d("Tag",id+"");

        ContactsDatabaseAdapter contactsDatabaseAdapter=new ContactsDatabaseAdapter(this);
        ///hashMap=contactsDatabaseAdapter.getParticularDoctor(id);

        //doctorName.setText(contactsDatabaseAdapter.getParticularDoctor(id));
        doctorName.setText(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("name")+"");
        speciality.setText(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("speciality")+"");
        number.setText(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("mobile")+"");
        email.setText(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("email")+"");
        schedule.setText(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("schedule")+"");
        address.setText(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("address")+"");

        //UID=Integer.parseInt(contactsDatabaseAdapter.getParticularDoctor(doc_name).get("uid")+"");

        //LogToast.L("ambulance",contactsDatabaseAdapter.getParticularDoctor(doc_name).get("name")+"   info");
        LogToast.L("ambulance",doctorName.getText().toString());


    }

    public void makeCall(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        String number= (String) this.number.getText();
        if(number.length()<5){
            Snackbar snackbar = Snackbar
                    .make(view, "Please enter a valid number \n"+number+" is not valid number", Snackbar.LENGTH_SHORT);
            View snackbarView=snackbar.getView();
            //#7c43bd
            //F44336
            snackbarView.setBackgroundColor(Color.parseColor("#7c43bd"));
            TextView textView=snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.BLACK);

            snackbar.show();

            //LogToast.T(this,"Please enter a valid number \n"+number+"is not valid number");
        }
        else{
            callIntent.setData(Uri.parse("tel:"+number));

            if (ActivityCompat.checkSelfPermission(DoctorInfoActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(callIntent);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_close:
                this.finish();
                return true;

            case R.id.action_edit:
                update(doc_name);
                this.finish();
                //LogToast.L("ambulance","update");
                return true;

            case R.id.action_delete:
                 int effectedRow=delete(doc_name);
                if(effectedRow<=0)
                {
                    LogToast.T(this,"Error ");
                }
                else
                {
                    LogToast.T(this,"Successfully deleted");
                }
                this.finish();
                return  true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_delete, menu);
        return true;
    }

    private int delete(String name)
    {
        contactsDatabaseHelper=new ContactsDatabaseAdapter.ContactsDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=contactsDatabaseHelper.getWritableDatabase();

        String whereargs[]={name};

        int row=sqLiteDatabase.delete(ContactsDatabaseAdapter.ContactsDatabaseHelper.getDoctorTable(),
                ContactsDatabaseAdapter.ContactsDatabaseHelper.getDoctorName()+" =? ",
                whereargs);
        return row;
    }

    private void update(String doctor_name)
    {
        boolean flag=true;
        Intent intent=new Intent(DoctorInfoActivity.this,DoctorAddActivity.class);
        intent.putExtra("doctor_name",doctorName.getText());
        intent.putExtra("speciality",speciality.getText());
        intent.putExtra("number",number.getText());
        intent.putExtra("email",email.getText());
        intent.putExtra("schedule",schedule.getText());
        intent.putExtra("address",address.getText());

        intent.putExtra("Specifiaction",flag);
        startActivity(intent);
    }

}
