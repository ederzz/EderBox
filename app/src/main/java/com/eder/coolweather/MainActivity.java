package com.eder.coolweather;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eder.coolweather.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment[] fragments;
    private String[] titles = new String[]{ "CoolWeather", "少数派" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = getFragments();
        init();
    }

    private Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[2];
        fragments[0] = new HomeFragment();
        fragments[1] = new NewsFragment();
        return fragments;
    }

    private void init() {
         tabLayout = findViewById(R.id.tablayout);
         for (String title : titles) {
             tabLayout.addTab(tabLayout.newTab().setText(title));
         }
         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });
         switchFragment(0);
    }

    private void switchFragment(int position) {
        // 切换tab fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragments[position]).commit();
    }
}