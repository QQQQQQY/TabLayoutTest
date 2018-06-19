package com.example.com.tablayouttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.tablayouttest.tabLayout.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentAdapter fragmentAdapter;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = findViewById(R.id.analytical_product_formula_tl);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ViewPager viewPager = findViewById(R.id.analytical_product_formula_vp);

        ArrayList<String> titles = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            titles.add("type" + i);
            Fragment fragment = new Fragment();
            fragments.add(fragment);
        }

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);

        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0, false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != 0) {
                    getFirstTab(tabLayout).setTextColor(Color.parseColor("#111111"));
                } else {
                    getFirstTab(tabLayout).setTextColor(Color.parseColor("#d85681"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setFirstTab(tabLayout);
    }

    public TextView getFirstTab(TabLayout tabLayout) {
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        LinearLayout child = (LinearLayout) linearLayout.getChildAt(0);
        return (TextView) child.getChildAt(1);
    }

    private void setFirstTab(final TabLayout tabLayout) {
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        LinearLayout child = (LinearLayout) linearLayout.getChildAt(0);
        child.setOrientation(LinearLayout.HORIZONTAL);
        child.setPadding(0, 0, 0, 0);
        // title
        TextView textView = (TextView) child.getChildAt(1);
        LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(lpText);
        lpText.setMargins(35, 0, 35, 0);
        lpText.gravity = Gravity.CENTER;
        textView.setTextColor(Color.parseColor("#111111"));
        // 分割线
        TextView divider = new TextView(this);
        LinearLayout.LayoutParams lpDivider = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        divider.setWidth(2);
        divider.setHeight(55);
        divider.setBackgroundColor(Color.parseColor("#cdcdd0"));
        divider.setLayoutParams(lpDivider);
        child.addView(divider);
    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments;
        ArrayList<String> titles;

        public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments,
                               ArrayList<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
