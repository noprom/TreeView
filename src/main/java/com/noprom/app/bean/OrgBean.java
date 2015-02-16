package com.noprom.app.bean;

/**
 * 树形控件Bean
 * 根据ListView's item + paddingLeft(level) + expand include
 * 将系统中的数据Bean -> Node
 *
 *
 * Created by noprom on 2015/2/16.
 */
public class OrgBean {
    private int _id;
    private int parentId;
    private String name;
}
