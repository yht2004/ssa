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
     * 根据传入的父节点ID获取子节点
     * @return
     */
    public static List<Menu> getchildren(List<Menu> list,Integer parentId){
        List<Menu> childList = new ArrayList<>();
        for (Iterator<Menu> it = list.iterator();it.hasNext();){
            Menu n = (Menu) it.next();
            if (n.getParentId() == parentId){

                recursion(list,n);
                childList.add(n);
            }
        }

        return childList;
    }

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
            if (n.getMenuId().intValue() == t.getParentId().intValue()){
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
                    recursion(list,m);
                }
            }
        }

    }

    public static List<Menu> getChild(Integer parentId,List<Menu> list){
        List<Menu> childList = new ArrayList<>();
        for (Menu m : list){
            if (m.getParentId() == parentId){
                childList.add(m);
            }
        }
        for (Menu n : childList){
            n.setChildren(getChild(n.getMenuId(),list));
        }
        if (childList.size() == 0){
            return new ArrayList<Menu>();
        }
        return childList;
    }

    /**
     * 判断是否存在子节点
     */
    public static boolean hasChild(List<Menu> list,Menu t){
        return getChildrenList(list,t).size() > 0 ? true : false;
    }
}
