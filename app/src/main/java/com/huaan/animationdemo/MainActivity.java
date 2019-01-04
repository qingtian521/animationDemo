package com.huaan.animationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huaan.animationdemo.activity.ButtonActivity;
import com.huaan.animationdemo.activity.CardSlidePanelActivity;
import com.huaan.animationdemo.adapter.CatalogAdapter;
import com.huaan.animationdemo.javabean.CatalogBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mViewRecycler;
    List<CatalogBean> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //
        CatalogAdapter adapter = new CatalogAdapter(getCatalogData());
        mViewRecycler.setLayoutManager(new LinearLayoutManager(this));
        mViewRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initView() {
        mViewRecycler = (RecyclerView) findViewById(R.id.view_recycler);
    }

    private List<CatalogBean> getCatalogData(){
        data.add(new CatalogBean("变化的提交按钮",0));
        data.add(new CatalogBean("拖动的view",1));
        return data;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getCode()){
            case 0:
                startActivity(new Intent(getApplicationContext(),ButtonActivity.class));
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(),CardSlidePanelActivity.class));
                break;
        }
    }
}
