package com.example.maruf.e_pharma.Trends;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shuvo.medicare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class stepCounterGraph extends Fragment {


    public stepCounterGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_counter, container,false);
        return view;
    }

}
