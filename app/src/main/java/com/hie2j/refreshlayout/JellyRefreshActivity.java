package com.hie2j.refreshlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

public class JellyRefreshActivity extends AppCompatActivity {

    private JellyRefreshLayout jellyRefreshLayout;
    private TextView txt_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelly_refresh);

        setTitle("Jelly");
        txt_1 = findViewById(R.id.txt_1);
        jellyRefreshLayout = findViewById(R.id.jelly_refresh);
        jellyRefreshLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jellyRefreshLayout.setRefreshing(false);
                        txt_1.setText(R.string.lorem_ipsum);
                    }
                },3000);
            }
        });
        View view = LayoutInflater.from(this).inflate(R.layout.activity_jelly_refresh,null);
        jellyRefreshLayout.setLoadingView(view);
    }
}
