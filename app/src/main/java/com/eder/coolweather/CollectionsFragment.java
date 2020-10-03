package com.eder.coolweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eder.coolweather.collections.SspaiFragment;
import com.eder.coolweather.collections.ViewPagerAdapter;
import com.eder.coolweather.collections.ZhihuFragment;
import com.eder.coolweather.util.Constants;
import com.google.android.material.tabs.TabLayout;

// my collections frag

public class CollectionsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);

        initTabs(view);
        return view;
    }

    private void initTabs(View v) {
        tabLayout = v.findViewById(R.id.collections_tabs);
        viewPager = v.findViewById(R.id.collections_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}