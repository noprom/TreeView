package com.noprom.app.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形控件节点
 * Created by noprom on 2015/2/16.
 */
public class Node {

    private int id;

    // 根节点
    private int pid = 0;

    public Node(){}
    public Node(int id, int pid, String level) {
        this.id = id;
        this.pid = pid;
        this.level = level;
    }

    // 树的层级
    private int level;

    // 当前状态 是否展开
    private boolean isExpand = false;

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isExpand() {
        return isExpand;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int icon;
    private Node parent;
    private List<Node> children = new ArrayList<Node>();

    /**
     * 判断是否是根节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null;
    }


    /**
     * 判断父节点是否展开
     *
     * @return
     */
    public boolean isParentExpand() {
        if (parent == null) return false;
        return parent.isExpand();
    }

    /**
     * 判断是否是叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * 计算当前节点的层级
     *
     * @return
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        // 如果当前节点不展开，那么其所有子节点也不展开
        if (!isExpand) {
            for (Node node : children) {
                node.setExpand(false);
            }
        }
    }
}
