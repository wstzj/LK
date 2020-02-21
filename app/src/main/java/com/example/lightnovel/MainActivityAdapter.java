package com.example.lightnovel;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lightnovel.PlatePackage.PlateFragment;
import com.example.lightnovel.Search.FakeSearchFragment;
import com.example.lightnovel.Search.RealSearchFragment;

public class MainActivityAdapter extends FragmentPagerAdapter {

    private String[] titles =new String[]{"虚假的搜索","真实的搜索","板块"};



    public MainActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==1){
            return new RealSearchFragment();
        }else if (position==2){
            return new PlateFragment();
        }
        return new FakeSearchFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
