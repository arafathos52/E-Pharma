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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

public class AmbulanceInfoActivity extends AppCompatActivity {

    TextView driverName,number,address;
    Button callDriver;
    String drive_name;

    ContactsDatabaseAdapter.ContactsDatabaseHelper contactsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_info);

        driverName=findViewById(R.id.a_driver_name);
        number=findViewById(R.id.a_phone_number);
        address=findViewById(R.id.a_address);
        callDriver=findViewById(R.id.a_call_button);

        drive_name=getIntent().getStringExtra("a_name");

        ContactsDatabaseAdapter contactsDatabaseAdapter=new ContactsDatabaseAdapter(this);
        ///hashMap=contactsDatabaseAdapter.getParticularDoctor(id);

        //doctorName.setText(contactsDatabaseAdapter.getParticularDoctor(id));
        driverName.setText(contactsDatabaseAdapter.getParticularAmbulance(drive_name).get("name")+"");
        number.setText(contactsDatabaseAdapter.getParticularAmbulance(drive_name).get("mobile")+"");
        address.setText(contactsDatabaseAdapter.getParticularAmbulance(drive_name).get("address")+"");
    }

    public void makeCall(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        String number= (String) this.number.getText();
        if(number.length()<5){
            Snackbar snackbar = Snackbar
                    .make(view, "Please enter a valid number \n"+number+" is not valid number", Snackbar.LENGTH_SHORT);
            View snackbarView=snackbar.getView();
            //#7c43bd
            snackbarView.setBackgroundColor(Color.parseColor("#7c43bd"));
            TextView textView=snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.BLACK);

            snackbar.show();

            //LogToast.T(this,"Please enter a valid number \n"+number+"is not valid number");
        }
        else{
            callIntent.setData(Uri.parse("tel:"+number));

            if (ActivityCompat.checkSelfPermission(AmbulanceInfoActivity.this,
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
                update(drive_name);
                this.finish();
                //LogToast.L("ambulance","update");
                return true;

            case R.id.action_delete:
                int effectedRow=delete(drive_name);
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

        int row=sqLiteDatabase.delete(ContactsDatabaseAdapter.ContactsDatabaseHelper.getAmbulanceTable(),
                ContactsDatabaseAdapter.ContactsDatabaseHelper.getDriverName()+" =? ",
                whereargs);
        return row;
    }

    private void update(String driver)
    {
        boolean flag=true;
        Intent intent=new Intent(AmbulanceInfoActivity.this,AmbulanceAddActivity.class);
        intent.putExtra("driver_name",driverName.getText());
        intent.putExtra("number",number.getText());
        intent.putExtra("address",address.getText());

        intent.putExtra("Specifiaction",flag);
        startActivity(intent);
    }

}
