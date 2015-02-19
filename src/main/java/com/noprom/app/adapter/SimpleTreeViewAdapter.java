package com.noprom.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.noprom.app.R;
import com.noprom.app.utils.Node;
import com.noprom.app.utils.TreeHelper;
import com.noprom.app.utils.adapter.TreeListViewAdapter;

import java.util.List;

/**
 * Created by noprom on 2015/2/18.
 */
public class SimpleTreeViewAdapter<T> extends TreeListViewAdapter<T> {
    public SimpleTreeViewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        super(tree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView) convertView.findViewById(R.id.id_item_icon);
            holder.mText = (TextView) convertView.findViewById(R.id.id_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1) {
            holder.mIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(node.getIcon());
        }

        holder.mText.setText(node.getName());

        return convertView;
    }

    /**
     * 动态插入节点
     *
     * @param position
     * @param s
     */
    public void addExtraNode(int position, String s) {
        Node node = mVisableNodes.get(position);
        int indexOf = mAllNodes.indexOf(node);

        Node extraNode = new Node(-1, node.getId(), s);
        extraNode.setParent(node);
        node.getChildren().add(extraNode);

        mAllNodes.add(indexOf + 1, extraNode);
        mVisableNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        notifyDataSetChanged();
    }


    private class ViewHolder {
        ImageView mIcon;
        TextView mText;
    }
}
