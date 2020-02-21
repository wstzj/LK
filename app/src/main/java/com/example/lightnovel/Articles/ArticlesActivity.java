package com.example.lightnovel.Articles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.lightnovel.MainActivityAdapter;
import com.example.lightnovel.R;
import com.google.android.material.tabs.TabLayout;

public class ArticlesActivity extends AppCompatActivity {

    private String url;


    private ViewPager viewPager;

    private DrawerLayout drawerLayout;

    private ArticlesAdapter adapter;
    private TabLayout tabLayout;
    private TabLayout.Tab one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcticles);
        Intent intent = getIntent();
        url=intent.getStringExtra("Url");
        System.out.println(url);
        drawerLayout=findViewById(R.id.articles_drawer);
        viewPager=findViewById(R.id.articles_viewPager);
        adapter=new ArticlesAdapter(getSupportFragmentManager(),url);
        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        one = tabLayout.getTabAt(0);
//        one.setIcon(R.mipmap.ic_launcher);
    }

}
