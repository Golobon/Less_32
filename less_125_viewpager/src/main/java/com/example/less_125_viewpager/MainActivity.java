package com.example.less_125_viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends FragmentActivity {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 10;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d(TAG,"onPageSelected, position = " + position);
            }
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset,
                                       int positionOffsetPixels) {
//                Log.d(TAG,"onPageScrolled, position = " + position +
//                        " onPageScrolled, positionOffset = " + positionOffset +
//                        " onPageScrolled, positionOffsetPixels = " + positionOffsetPixels);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d(TAG,"onPageScrollStateChanged, state = " + state);
            }
        });
    }
    static class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Title " + position;
        }
    }
}