package com.suning.compare;

import java.util.TreeSet;

/**
 * @Author lynn
 * @Date 2020/9/3 20:07
 */
public class CompareTest02 {
    public static void main(String[] args) {
        TreeSet<Person02> treeSet = new TreeSet<>();
        treeSet.add(new Person02("zhangsan", 18));
        treeSet.add(new Person02("lisi", 19));
        treeSet.add(new Person02("wangwu", 10));
        System.out.println(treeSet);
    }

}
