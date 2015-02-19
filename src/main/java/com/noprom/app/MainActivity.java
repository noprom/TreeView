package com.noprom.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.noprom.app.adapter.SimpleTreeViewAdapter;
import com.noprom.app.bean.FileBean;
import com.noprom.app.utils.Node;
import com.noprom.app.utils.adapter.TreeListViewAdapter;

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

        // 设置适配器
        try {
            mAdapter = new SimpleTreeViewAdapter<FileBean>(mTree, this, mDatas, 1);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 初始化事件
        initEvent();


    }


    private void initEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    Toast.makeText(MainActivity.this, node.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        // 新增节点
        mTree.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final EditText et = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this).setTitle("新增节点").setView(et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(et.getText().toString())) return;
                        mAdapter.addExtraNode(position, et.getText().toString());
                    }
                }).setNegativeButton("取消", null).show();
                return true;
            }
        });
    }


    private void initDatas() {
        mDatas = new ArrayList<FileBean>();
        FileBean fileBean = new FileBean(1, 0, "根目录1");
        mDatas.add(fileBean);
        fileBean = new FileBean(2, 0, "根目录2");
        mDatas.add(fileBean);
        fileBean = new FileBean(3, 0, "根目录3");
        mDatas.add(fileBean);

        fileBean = new FileBean(4, 1, "根目录1-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(5, 1, "根目录1-2");
        mDatas.add(fileBean);
        fileBean = new FileBean(6, 5, "根目录1-2-1");
        mDatas.add(fileBean);

        fileBean = new FileBean(7, 3, "根目录3-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(8, 3, "根目录3-2");
        mDatas.add(fileBean);
    }


}
