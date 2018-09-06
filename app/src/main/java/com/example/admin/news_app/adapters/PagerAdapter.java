package com.example.admin.news_app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.news_app.fragments.NewsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    static String[] categories = {"football", "hockey", "tennis", "basketball", "volleyball", "cybersport"};

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
            Fragment fragment = NewsFragment.newInstance(categories[position]);
            return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
