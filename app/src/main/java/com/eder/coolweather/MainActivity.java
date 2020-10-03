package com.eder.coolweather;
import android.os.Bundle;
import com.eder.coolweather.util.Constants;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = getFragments();
        initTabs();
    }

    private Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[2];
        fragments[0] = new CollectionsFragment();
        fragments[1] = new HomeFragment();
        return fragments;
    }

    private void initTabs() {
         tabLayout = findViewById(R.id.tablayout);
         for (String tab : Constants.TABS) {
             tabLayout.addTab(tabLayout.newTab().setText(tab));
         }
         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 switchTab(tab.getPosition());
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {
             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {
             }
         });
        switchTab(0);
    }

    private void switchTab(int position) {
        // 切换tab fragment
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.home_container,
            fragments[position]).commit();
    }
}