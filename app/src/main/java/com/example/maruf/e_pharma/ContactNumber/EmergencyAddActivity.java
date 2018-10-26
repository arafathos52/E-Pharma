package com.example.maruf.e_pharma.ContactNumber;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.shuvo.medicare.R;

//This class add emergency to the database

public class EmergencyAddActivity extends AppCompatActivity {

    ContactsDatabaseAdapter.ContactsDatabaseHelper contactsDatabaseHelper;

    EditText type;
    EditText phoneNumber;

    boolean fromUpdate=false;

    ContactsDatabaseAdapter contactsDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_add);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ActionBar actionBar=getSupportActionBar();
        //actionBar.hide();

        type= (EditText) findViewById(R.id.e_name_edit_text);
        phoneNumber= (EditText) findViewById(R.id.e_phone_number_edit_text);

        //when doctor info is needed to update this portion triggered
        //and add new emergency with previous info and the updated value

        if(getIntent().getBooleanExtra("Specifiaction",false))
        {
            fromUpdate=true;
            type.setText(getIntent().getStringExtra("type_name"));
            phoneNumber.setText(getIntent().getStringExtra("number"));

            String name=getIntent().getStringExtra("type_name");

            delete(name);
        }

        contactsDatabaseHelper=new ContactsDatabaseAdapter.ContactsDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=contactsDatabaseHelper.getWritableDatabase();

        contactsDatabaseAdapter=new ContactsDatabaseAdapter(this);
    }

    //this delete the previous row with is needed to update
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

    //Give options for adding confirmation & close options in the menubar
    //If done selected data is inserted in the database and show a toast
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_close:
                this.finish();
                return true;

            case R.id.action_done:
                long id=0;
                int typeNameTest=type.getText().toString().length();
                if(typeNameTest<1)
                {
                    LogToast.T(this,"Please enter type");
                }
                else
                {
                    id=contactsDatabaseAdapter.insertEmergencyData(type.getText().toString(),
                            phoneNumber.getText().toString());
                }

                if(id<0 ){
                    LogToast.T(this,"Failed to Add");
                }else{
                    if(typeNameTest>1 && !fromUpdate)
                    {

                        LogToast.T(this,"Successfully added");

                        this.finish();
                    }
                    else if(typeNameTest>1 && fromUpdate)
                    {
                        LogToast.T(this,"Successfully updated");

                        this.finish();
                    }

                }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    //This show the toolbar in action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor, menu);
        return true;
    }
}
