package com.example.esioner.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GestureDetector gestureDetector;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mContext = this;

        ListView listView = findViewById(R.id.list);
        ScrollView scrollView = findViewById(R.id.scroll_view);

        initArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: scrollX = " + scrollX);
                Log.d(TAG, "onScrollChange: scrollY = " + scrollY);
                Log.d(TAG, "onScrollChange: oldScrollX = " + oldScrollX);
                Log.d(TAG, "onScrollChange: oldScrollY = " + oldScrollY);
            }
        });
    }

    List list = new ArrayList();

    private void initArray() {
        for (int i = 0; i < 100; i++) {
            list.add("这事文字内容 " + i + "" + i);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
