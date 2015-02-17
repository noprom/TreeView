package com.noprom.app.utils;

import android.util.Log;

import com.noprom.app.R;
import com.noprom.app.utils.annotation.TreeNodeId;
import com.noprom.app.utils.annotation.TreeNodeLabel;
import com.noprom.app.utils.annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形控件管理器
 * Created by noprom on 2015/2/16.
 */
public class TreeHelper {


    private static Node nodeIcon;

    /**
     * 将数据转化为节点
     * 通过反射与注解获得某个Bean的id,pid和name
     *
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> convertDatasToNodes(List<T> datas) throws IllegalAccessException {

        List<Node> nodes = new ArrayList<Node>();
        Node node = null;
        for (T t : datas) {
            int id = -1;
            int pid = -1;
            String label = null;

            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    field.setAccessible(true);
                    id = field.getInt(t);
                }

                if (field.getAnnotation(TreeNodePid.class) != null) {
                    field.setAccessible(true);
                    pid = field.getInt(t);
                }

                if (field.getAnnotation(TreeNodeLabel.class) != null) {
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }
            }

            node = new Node(id, pid, label);
            nodes.add(node);
        }

        // 设置Node之间的关联关系
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = 0; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getPid() == n.getId()) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getPid()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }

        // 设置图片
        for (Node n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    /**
     * 为节点n设置图标
     *
     * @param n
     */
    private static void setNodeIcon(Node n) {
        if (n.getChildren().size() > 0 && n.isExpand()) {
            n.setIcon(R.drawable.tree_ex);
        } else if (n.getChildren().size() > 0 && !n.isExpand()) {
            n.setIcon(R.drawable.tree_ec);
        } else {
            n.setIcon(-1);
        }
    }

    /**
     * 为用户数据排序
     *
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = convertDatasToNodes(datas);

        // 获取树的根节点
        List<Node> rootNodes = getRootNodes(nodes);

        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        Log.d("TAG","size = "+result.size());
        return result;
    }


    /**
     * 将一个节点的所有孩子节点放入result
     * 递归实现
     *
     * @param result
     * @param node
     * @param defaultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {
        result.add(node);
        if (defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf()) return;

        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(result, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
        }

    }


    /**
     * 过滤出所有可见的节点
     *
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 获得所有的根节点
     *
     * @param nodes
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();

        for (Node node : nodes) {
            if (node.isRoot()) {
                root.add(node);
            }
        }
        Log.d("TAG","root size = "+root.size());
        return root;
    }
}
