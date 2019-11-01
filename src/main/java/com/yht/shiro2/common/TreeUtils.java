package com.yht.shiro2.common;

import com.yht.shiro2.project.menu.entity.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 权限工具类
 */
public class TreeUtils {



    /**
     * 获取子节点
     * @param list
     * @param t
     * @return
     */
    public static List<Menu> getChildrenList(List<Menu> list,Menu t){
        List<Menu> childList = new ArrayList<>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext()){
            Menu n = (Menu) it.next();
            if (n.getMenuId() == t.getParentId()){
                childList.add(n);
            }
        }
        return childList;
    }


    /**
     * 递归列表
     */
    public static void recursion(List<Menu> list,Menu t){
        List<Menu> childrenList = getChildrenList(list, t);
        t.setChildren(childrenList);
        for (Menu tchild : childrenList){
            if (hasChild(list,tchild)){
                Iterator<Menu> it = childrenList.iterator();
                while (it.hasNext()){
                    Menu m = (Menu) it.next();
                    recursion(list,tchild);
                }
            }
        }

    }

    /**
     * 判断是否存在子节点
     */
    public static boolean hasChild(List<Menu> list,Menu t){
        return getChildrenList(list,t).size() > 0 ? true : false;
    }
}
