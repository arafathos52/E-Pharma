package com.example.maruf.e_pharma.physicalFitness;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.shuvo.medicare.R;
import com.example.shuvo.medicare.Trends.Calories.Calorie_calculator;
import com.example.shuvo.medicare.Trends.Weight_Graph.weight_graph;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class physical_fitness extends AppCompatActivity {

    BottomBar bottombar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_fitness);
        bottombar = findViewById(R.id.bottomBarPhysical);
        bottombar = BottomBar.attach(this,savedInstanceState);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        bottombar.setItemsFromMenu(R.menu.bottommenu2, new OnMenuTabClickListener() {


            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
               if(menuItemId == R.id.Calorie)
                {
                    Calorie_calculator bmi = new Calorie_calculator();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.bottomTabPhysical, bmi);
                    fragmentTransaction.commit();
                    //  bottombar.getBar().setBackgroundColor(getResources().getColor(R.color.tab2));
                    bottombar.setActiveTabColor("#DD2C00");

                }
                else if(menuItemId == R.id.weight_tab)
                {
                    weight_graph weight = new weight_graph();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.bottomTabPhysical, weight);
                    fragmentTransaction.commit();
                    //   bottombar.getBar().setBackgroundColor(getResources().getColor(R.color.tab3));
                    bottombar.setActiveTabColor("#DD2C00");

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
