package com.example.lightnovel.Articles;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ArticlesAdapter extends FragmentPagerAdapter {

    private String url;
    private String[] titles=new String[]{"test"};

    public ArticlesAdapter(FragmentManager fm, String url) {
        super(fm);
        this.url = url;
    }

    @Override
    public Fragment getItem(int position) {
        ArticlesFragment articlesFragment =new ArticlesFragment(url);
        return articlesFragment;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
