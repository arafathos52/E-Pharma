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

public class EmergencyInfoActivity extends AppCompatActivity {

    TextView typeName,number;
    Button callEmergency;
    String type;

    ContactsDatabaseAdapter.ContactsDatabaseHelper contactsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_info);

        typeName=findViewById(R.id.e_type_name);
        number=findViewById(R.id.e_phone_number);
        callEmergency=findViewById(R.id.e_call_button);

        type=getIntent().getStringExtra("e_name");

        ContactsDatabaseAdapter contactsDatabaseAdapter=new ContactsDatabaseAdapter(this);
        ///hashMap=contactsDatabaseAdapter.getParticularDoctor(id);

        //doctorName.setText(contactsDatabaseAdapter.getParticularDoctor(id));
        typeName.setText(contactsDatabaseAdapter.getParticularEmergency(type).get("name")+"");
        number.setText(contactsDatabaseAdapter.getParticularEmergency(type).get("mobile")+"");
    }

    public void makeCall(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        String number= (String) this.number.getText();
        if(number.length()<3){

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

            if (ActivityCompat.checkSelfPermission(EmergencyInfoActivity.this,
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
                update(type);
                this.finish();
                LogToast.L("ambulance","update");
                return true;

            case R.id.action_delete:
                int effectedRow=delete(type);
                if(effectedRow<=0)
                {
                    LogToast.T(this,"Error "+effectedRow);
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

        int row=sqLiteDatabase.delete(ContactsDatabaseAdapter.ContactsDatabaseHelper.getEmergencyTable(),
                ContactsDatabaseAdapter.ContactsDatabaseHelper.getTYPE()+" =? ",
                whereargs);
        return row;
    }

    private void update(String type)
    {
        boolean flag=true;
        Intent intent=new Intent(EmergencyInfoActivity.this,EmergencyAddActivity.class);
        intent.putExtra("type_name",typeName.getText());
        intent.putExtra("number",number.getText());

        intent.putExtra("Specifiaction",flag);
        startActivity(intent);
    }
}
