package com.noprom.app.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形控件管理器
 * Created by noprom on 2015/2/16.
 *
 */
public class TreeHelper {


    /**
     * 将数据转化为节点
     * 通过反射与注解获得某个Bean的id,pid和name
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> convertDatasToNodes(List<T> datas) {

        List<Node> nodes = new ArrayList<Node>();
        Node node = null;
        for (T t:datas){
            node = new Node();

        }
        return null;
    }
}
