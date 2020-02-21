package com.example.lightnovel.PlatePackage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.lightnovel.Articles.ArticlesActivity;
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

public class PlateFragment extends Fragment {

    private List<Plate> plateList=new ArrayList<>();

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textView;

    private String url="https://www.lightnovel.cn/";
    private String responseData;
    private PlateAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    private TextView upDateUrl;

    String urlForUpDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plate,container,false);
        initRecyclerView(view);
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        RequestDate();

        return view;
    }
    private void RequestDate(){
        plateList.clear();
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
                    parseHtml(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void spiderPlateFirst(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.select("#category_3 > table > tbody > tr");
        for(Element element : elements){

            String name=element.select("td:nth-child(2) > h2 > a").text();
            String urlForPlate=element.select("td:nth-child(2) > h2 > a[href]").attr("abs:href");
            String lastUpdate=element.select("td.fl_by > div > a").text();
            String urlForLastUpdate=element.
                    select("td.fl_by > div > a[href]").
                    attr("abs:href");
            String urlForImage=element.select("td.fl_icn > a > img").attr("abs:src");

            if(name.length()!=0){
                //System.out.println("a"+name+"b"+urlForImage+"tttt"+lastUpdate+urlForPlate+urlForPlate);
                Plate plate = new Plate(name, urlForImage, lastUpdate, urlForPlate, urlForLastUpdate);
                plateList.add(plate);
                Log.e("DATE>>", plate.toString() + "1111111111");}

        }
    }
    private void spiderPlateSecond(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.select("#category_7 > table > tbody > tr");
        for(Element element : elements){

            String name=element.select("td:nth-child(2) > h2 > a").text();
            String urlForPlate=element.select("td:nth-child(2) > h2 > a[href]").attr("abs:href");
            String lastUpdate=element.select("td.fl_by > div > a").text();
            String urlForLastUpdate=element.select("td.fl_by > div > a[href]").attr("abs:href");
            String urlForImage=element.select("td.fl_icn > a > img").attr("abs:src");
            if(name.length()!=0){
                Plate plate = new Plate(name, urlForImage, lastUpdate, urlForPlate, urlForLastUpdate);
                plateList.add(plate);
                Log.e("DATE>>", plate.toString() + "222222");}
        }
    }
    private void spiderPlateThird(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.select("#category_18 > table > tbody > tr");
        for(Element element : elements){

            String name=element.select("td:nth-child(2) > h2 > a").text();
            String urlForPlate=element.select("td:nth-child(2) > h2 > a[href]").attr("abs:href");
            String lastUpdate=element.select("td.fl_by > div > a").text();
            String urlForLastUpdate=element.select("td.fl_by > div > a[href]").attr("abs:href");
            String urlForImage=element.select("td.fl_icn > a > img").attr("abs:src");
            if(name.length()!=0){
            Plate plate = new Plate(name,urlForImage,lastUpdate,urlForPlate,urlForLastUpdate);
            plateList.add(plate);
            Log.e("DATE>>",plate.toString()+"333333");}

        }
    }

    private void initRecyclerView(View view){
        recyclerView=view.findViewById(R.id.plate_recyclerView);
        progressBar=view.findViewById(R.id.progressbar_refreshing);
        textView=view.findViewById(R.id.text_loading);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setProgressViewOffset(true,-20,100);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("有没有刷新啊");
                plateList.clear();
                RequestDate();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

    }
    private void parseHtml(final String html){
        spiderPlateFirst(html);
        spiderPlateSecond(html);
        spiderPlateThird(html);
        Activity activity=getActivity();
        if(activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    progressBar.setVisibility(View.GONE);
                    System.out.println("ccccccccccccccccccccccc");
                    textView.setVisibility(View.GONE);
                    adapter = new PlateAdapter(plateList, getActivity());
                    recyclerView.setAdapter(adapter);
                    adapter.setItemClickListener(itemClickListener);
                    swipeRefreshLayout.setRefreshing(false);
                }

            });
        }
    }
    private PlateAdapter.OnItemClickListener itemClickListener=new PlateAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {

            switch (v.getId()){
                case R.id.plate_entering:
                    System.out.println("？？？？？");
                    Button plateUrl=v.findViewById(R.id.plate_entering);
                    String urlForPlate=plateUrl.getText().toString();
                    Intent intent =new Intent();
                    intent.putExtra("Url",urlForPlate);
                    intent.setClass(getActivity(), ArticlesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.last_respond_text:
                    //upDateUrl=v.findViewById(R.id.url_for_update);
//                    plateUrl=view.findViewById(R.id.url_for_plate);
                    //urlForUpDate=upDateUrl.getText().toString();
                    //System.out.println("bbb"+urlForUpDate);
                    break;

                default:
                    System.out.println("ccc");
                    break;
            }
        }
        @Override
        public void onItemLongClick(View v) {
        }
    };
}

