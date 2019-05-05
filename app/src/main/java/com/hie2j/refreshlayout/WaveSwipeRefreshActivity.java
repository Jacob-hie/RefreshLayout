package com.hie2j.refreshlayout;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class WaveSwipeRefreshActivity extends AppCompatActivity {

    private ListView listView;
    private WaveSwipeRefreshLayout waveSwipeRefreshLayout;

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_swipe_refresh);

        listView = findViewById(R.id.main_list);
        waveSwipeRefreshLayout = findViewById(R.id.main_swipe);

        waveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE,Color.WHITE);
        waveSwipeRefreshLayout.setWaveColor(Color.argb(100,255,0,0));
        waveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < 60; i++) {
                            data.add("测试数据：" + i );
                        }
                        adapter.notifyDataSetChanged();
                        waveSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        
        adapter = new ArrayAdapter<String>(
                WaveSwipeRefreshActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }
}
