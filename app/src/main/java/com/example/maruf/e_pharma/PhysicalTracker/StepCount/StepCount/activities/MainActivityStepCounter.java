package com.example.maruf.e_pharma.PhysicalTracker.StepCount.StepCount.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.fragments.DailyReportFragment;
import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.fragments.MainFragment;
import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.utils.StepDetectionServiceHelper;
import com.example.shuvo.medicare.R;


/**
 * Main view incl. navigation drawer and fragments
 */
public class MainActivityStepCounter extends AppCompatActivity implements DailyReportFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_step_counter);
        Log.d("pt","done");

        // init preferences
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);

        // Load first view
        try{
            final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, new MainFragment(), "MainFragment");
            fragmentTransaction.commit();
        }catch (Exception e){
            Log.d("pt",e.getMessage());
        }

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Log.d("pt","done 0");

        // Start step detection if enabled and not yet started
        StepDetectionServiceHelper.startAllIfEnabled(this);
        Log.d("pt","done 2");
        //Log.i(LOG_TAG, "MainActivity initialized");
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
