package com.noprom.app.bean;

import com.noprom.app.utils.annotation.TreeNodeId;
import com.noprom.app.utils.annotation.TreeNodeLabel;
import com.noprom.app.utils.annotation.TreeNodePid;

/**
 * 树形控件Bean
 * 根据ListView's item + paddingLeft(level) + expand include
 * 将系统中的数据Bean -> Node
 * Created by noprom on 2015/2/16.
 */
public class FileBean {
    @TreeNodeId
    private int id;
    @TreeNodePid
    private int pid;
    @TreeNodeLabel
    private String label;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
