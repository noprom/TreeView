package com.noprom.app.utils;

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
            String label =null;

            node = new Node();
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotations(TreeNodeId.class)!=null){
                    field.setAccessible(true);
                    id = field.getInt(t);
                }

                if (field.getAnnotations(TreeNodePid.class)!=null){
                    field.setAccessible(true);
                    pid = field.getInt(t);
                }

                if (field.getAnnotations(TreeNodeLabel.class)!=null){
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }

                node = new Node(id,pid,label);
                nodes.add(node);
            }

        }
        return nodes;
    }
}
