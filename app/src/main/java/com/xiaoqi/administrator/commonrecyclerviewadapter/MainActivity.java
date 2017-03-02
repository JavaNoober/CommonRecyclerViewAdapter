package com.xiaoqi.administrator.commonrecyclerviewadapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add(i);
        }
        recyclerView.setAdapter(new CommonRecyclerViewAdapter<Integer>(this, list, R.layout.item) {
            @Override
            public void convert(RecyclerViewHolder holder, Integer item, int position) {
                holder.setText(R.id.text, item + "");
            }
        });
    }
}
