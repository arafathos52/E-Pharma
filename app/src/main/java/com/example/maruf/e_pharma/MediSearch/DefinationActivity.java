package com.example.maruf.e_pharma.MediSearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

public class DefinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defination);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        String medicine=getIntent().getStringExtra("Medicine");
        String generic = getIntent().getStringExtra("Generic");
        String uses = getIntent().getStringExtra("Used For");
        String sideeffects = getIntent().getStringExtra("Side Effects");
        String dosage = getIntent().getStringExtra("Dosage");
        String brand = getIntent().getStringExtra("Brand_Names");

        TextView medicinetext = (TextView) findViewById(R.id.medicinetext);
        TextView generictext = (TextView) findViewById(R.id.generictext);
        TextView usestext = (TextView) findViewById(R.id.usestext);
        TextView sideeffectstext = (TextView) findViewById(R.id.sideeffectstext);
        TextView dosagetext = (TextView) findViewById(R.id.dosagetext);
        TextView brandtext = (TextView) findViewById(R.id.brandtext);

        medicinetext.setText(medicine);
        generictext.setText(generic);
        usestext.setText(uses);
        sideeffectstext.setText(sideeffects);
        dosagetext.setText(dosage);
        brandtext.setText(brand);

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
