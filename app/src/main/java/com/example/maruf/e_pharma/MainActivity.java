package com.example.maruf.e_pharma;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.maruf.e_pharma.ContactNumber.ContactActivity;
import com.example.maruf.e_pharma.CurrentEpidemic.CurrentEpidemicFragment;
import com.example.maruf.e_pharma.First_Aid.FirstAid_fragment;
import com.example.maruf.e_pharma.Gallery_second.Second_gallery;
import com.example.maruf.e_pharma.Maps.MapsMainActivity;
import com.example.maruf.e_pharma.MediSearch.SearchMain;
import com.example.maruf.e_pharma.Reminder.EventPage;
import com.example.maruf.e_pharma.Reminder.MyAlarm;
import com.example.maruf.e_pharma.Trends.Trends_Main;
import com.example.maruf.e_pharma.User_Profile.LoginPage;
import com.example.maruf.e_pharma.User_Profile.Profile;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG="Tag";
    private Button profile;
    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDrawer();
        /*if (getIntent().getIntExtra("fragmentNumber", 0) == 1) {
            FragmentManager fm = MainActivity.this.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            FirstAid_fragment fragment = new FirstAid_fragment();

            if (fragment != null) {
                // Replace current fragment by this new one
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();

            }
        }*/
        //boolean getting for notification
        if (getIntent().getExtras() != null) {
            Bundle b = getIntent().getExtras();
            boolean cameFromNotification = b.getBoolean("fromNotification");
            if(cameFromNotification)
            {
                MyAlarm.mediaPlayer.stop();;
            }
        }








    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.timeline) {
            if(FirebaseAuth.getInstance().getCurrentUser()==null)
            {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            }
            else {
                EventPage epage = new EventPage();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, epage);
                fragmentTransaction.commit();
            }

        } else if (id == R.id.medicine) {
            SearchMain epage = new SearchMain();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, epage);
            fragmentTransaction.commit();

        }else if(id==R.id.current_epidemic){
            CurrentEpidemicFragment currentEpidemicFragment=new CurrentEpidemicFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, currentEpidemicFragment,"");
            fragmentTransaction.commit();
            //Log.d(TAG,"after transition");
        }
        else if (id == R.id.trends) {
            Intent intent = new Intent(this, Trends_Main.class);
            startActivity(intent);


        } else if (id == R.id.physical_tracker) {
            /*Intent intent=new Intent(this, MainActivityStepCounter.class);
            startActivity(intent);
*/
            Intent intent = new Intent(this, Second_gallery.class);
            startActivity(intent);

        } else if (id == R.id.contacts) {

            //getSupportActionBar().hide();
            Log.d(TAG,"before");
            Intent intent = new Intent(this, ContactActivity.class);

            startActivity(intent);
            Log.d(TAG,"after");

        } else if (id == R.id.map) {
            Intent intent = new Intent(this, MapsMainActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.first_aid)
        {
            FirstAid_fragment faid = new FirstAid_fragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, faid);
            fragmentTransaction.commit();
        }
        else if (id == R.id.login) {
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);

        }
        else if(id==R.id.profile)
        {
            if(FirebaseAuth.getInstance().getCurrentUser()==null)
            {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
            }

        }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void showDrawer()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);


        navigationView.setNavigationItemSelectedListener(this);
    }



}
