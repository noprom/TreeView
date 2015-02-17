package com.noprom.app.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.noprom.app.utils.Node;
import com.noprom.app.utils.TreeHelper;

import java.util.List;

/**
 * Created by noprom on 2015/2/18.
 */
public abstract class TreeListViewAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<Node> mAllNodes;
    private List<Node> mVisableNodes;
    private LayoutInflater mInflater;
    private ListView mTree;

    private interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    private OnTreeNodeClickListener mListener;

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener listener) {
        mListener = listener;
    }

    public TreeListViewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        this.mTree = tree;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        this.mVisableNodes = TreeHelper.filterVisibleNodes(mAllNodes);

        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
                if (mListener != null) {
                    mListener.onClick(mVisableNodes.get(position), position);
                }
            }
        });
    }

    /**
     * 点击收缩或者展开
     *
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node node = mVisableNodes.get(position);
        if (node != null) {
            if (node.isLeaf()) return;
            node.setExpand(!node.isExpand());

            mVisableNodes = TreeHelper.filterVisibleNodes(mAllNodes);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mVisableNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisableNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisableNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);

        // 设置内边距
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
