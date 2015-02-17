package com.noprom.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.noprom.app.adapter.SimpleTreeViewAdapter;
import com.noprom.app.bean.FileBean;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView mTree;
    private SimpleTreeViewAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTree = (ListView) findViewById(R.id.id_listview);
        initDatas();
        try {
            mAdapter = new SimpleTreeViewAdapter<FileBean>(mTree, this,mDatas,0);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private void initDatas() {
        mDatas = new ArrayList<FileBean>();
        FileBean fileBean = new FileBean(1,0,"根目录1");
        mDatas.add(fileBean);
        fileBean = new FileBean(2,0,"根目录2");
        mDatas.add(fileBean);
        fileBean = new FileBean(3,0,"根目录3");
        mDatas.add(fileBean);

        fileBean = new FileBean(4,1,"根目录1-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(5,1,"根目录1-2");
        mDatas.add(fileBean);
        fileBean = new FileBean(6,5,"根目录1-2-1");
        mDatas.add(fileBean);

        fileBean = new FileBean(7,3,"根目录3-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(8,3,"根目录3-2");
        mDatas.add(fileBean);
    }


}
