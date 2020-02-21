package com.example.lightnovel.Articles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lightnovel.Articles.Articles;
import com.example.lightnovel.Articles.ArticlesListAdapter;
import com.example.lightnovel.Forum.ForumActivity;
import com.example.lightnovel.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ArticlesFragment extends Fragment {

    private List<Articles> articlesList =new ArrayList<>();
    private String responseData;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textView;
    private ArticlesListAdapter articlesListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String url;

    public ArticlesFragment(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_articles,container,false);
        initRecyclerView(view);

        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        ResponseDate();
        return view;
    }

    private void ResponseDate(){
        System.out.println("哼啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊0");
        articlesList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder().
                        connectTimeout(20, TimeUnit.SECONDS).
                        readTimeout(20,TimeUnit.SECONDS).build();
                Request request=new Request.Builder().url(url).build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    responseData=response.body().string();
                    System.out.println("哼啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊1");
                    parseHtml(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void initRecyclerView(View view){

        recyclerView=view.findViewById(R.id.articles_recyclerView);
        progressBar=view.findViewById(R.id.articles_refreshing);
        textView=view.findViewById(R.id.articles_loading);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ResponseDate();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);



    }
    private void parseHtml(String html){
        spiderArticles(html);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                articlesListAdapter = new ArticlesListAdapter(articlesList,getActivity());
                recyclerView.setAdapter(articlesListAdapter);
                articlesListAdapter.setItemClickListener(clickListener);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    private void spiderArticles(String html){
        System.out.println("哼啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊2");
        Document document = Jsoup.parse(html);
        Elements elements = document.select("#threadlisttableid> tbody");
        for(Element element : elements){
            String title=element.select("tr > th > a.s.xst").text();
            String urlTitle=element.select("tr > th > a.s.xst").attr("abs:href");
            String upDaterName=element.select("tr > td:nth-child(3)> cite> a").text();


            String tagOne=element.select("tr > th > em > a> font").text();
            String tagTwo=element.select("tr > th > em > a> b> font").text();

            String lastUpdateTime=element.select("tr >td:nth-child(3)> em > span").text();

            System.out.println(title+urlTitle+tagOne+"1"+tagTwo+"2"+upDaterName+lastUpdateTime);
            if(title.length()!=0){
            Articles articles =new Articles(tagOne,title,lastUpdateTime,upDaterName,urlTitle);
            articlesList.add(articles);
            Log.e("DATE>>","333333");
            }
        }
    }
    private ArticlesListAdapter.OnItemClickListener clickListener=new ArticlesListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()){
                case R.id.entering:
                    System.out.println("aaaaa");
                    Button url =v.findViewById(R.id.entering);
                    String urlForArticles =url.getText().toString();
                    Intent intent =new Intent();
                    intent.putExtra("Articles",urlForArticles);
                    intent.setClass(getActivity(), ForumActivity.class);
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };

}
