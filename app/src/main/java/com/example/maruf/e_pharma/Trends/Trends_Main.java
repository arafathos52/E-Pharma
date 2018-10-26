package com.example.maruf.e_pharma.Trends;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shuvo.medicare.R;
import com.example.shuvo.medicare.Trends.Calories.Calorie_calculator;
import com.example.shuvo.medicare.Trends.Weight_Graph.weight_graph;
import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class Trends_Main extends AppCompatActivity {
    BottomBar bottombar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends__main);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        bottombar = findViewById(R.id.bottomBar);
        bottombar = BottomBar.attach(this,savedInstanceState);
        bottombar.setItemsFromMenu(R.menu.bottom_menu, new OnMenuTabClickListener() {


            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                if(menuItemId == R.id.Calorie)
                {
                    Calorie_calculator bmi = new Calorie_calculator();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.bottomTabFrame, bmi);
                    fragmentTransaction.commit();
                  //  bottombar.getBar().setBackgroundColor(getResources().getColor(R.color.tab2));
                    bottombar.setActiveTabColor("#DD2C00");

                }
                else if(menuItemId == R.id.weight_tab)
                {
                    if(FirebaseAuth.getInstance().getCurrentUser()==null)
                    {
                        Toast.makeText(getApplication(), "Please login first", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        weight_graph weight = new weight_graph();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.bottomTabFrame, weight);
                        fragmentTransaction.commit();
                        //   bottombar.getBar().setBackgroundColor(getResources().getColor(R.color.tab3));
                        bottombar.setActiveTabColor("#DD2C00");
                    }

                }
                bottombar.setBackgroundColor(getResources().getColor(R.color.bottom_bar));



            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

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
}
