package com.hie2j.refreshlayout;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SwipeRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        listView = findViewById(R.id.mylist);

        initHandler();
        adapter = new ArrayAdapter<String>(SwipeRefreshActivity.this,R.layout.list_item,data);
        listView.setAdapter(adapter);

//        1、setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener):设置手势滑动监听器。
//        2、setProgressBackgroundColor(int colorRes):设置进度圈的背景色。
//        3、setColorSchemeResources(int… colorResIds):设置进度动画的颜色。
//        4、setRefreshing(Boolean refreshing):设置组件的刷洗状态。
//        5、setSize(int size):设置进度圈的大小，只有两个值：DEFAULT、LARGE

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.colorPrimaryDark);
        swipeRefreshLayout.setProgressViewEndTarget(true,100);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int i=0; i<10; i++){
                            data.add("测试数据: " + i);
                        }
                        handler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
    }

    private void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        //swipeRefreshLayout.setEnabled(false);
                        break;
                        default:
                            break;
                }
            }
        };
    }
}
