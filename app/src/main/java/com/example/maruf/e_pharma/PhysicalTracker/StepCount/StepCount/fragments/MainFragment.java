package com.example.maruf.e_pharma.PhysicalTracker.StepCount.StepCount.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.models.WalkingMode;
import com.example.shuvo.medicare.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Displays the main app view.
 * In general it's an overview over the users daily, weekly and monthly reports.
 *
 */
public class MainFragment extends Fragment {
    private Map<Integer, WalkingMode> menuWalkingModes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_step_counter, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setSubtitle(R.string.action_main);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        container.removeAllViews();

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        setupViewPager(viewPager);

        /*TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/

        setHasOptionsMenu(true);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        new ViewPagerAdapter(null);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //TODO monthly & weekly  can be added here
        adapter.addFragment(DailyReportFragment.newInstance(), getString(R.string.day));
        viewPager.setAdapter(adapter);
    }




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
