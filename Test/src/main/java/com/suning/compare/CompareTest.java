package com.suning.compare;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author lynn
 * @Date 2020/9/3 19:57
 */
public class CompareTest {
    public static void main(String[] args) {
        //创建临时的类
        TreeSet<Person> treeSet = new TreeSet<>(new MyPerson());
        //lambda表达式
        TreeSet<Person> treeSet1 = new TreeSet<>((Comparator<Person>) (p1, p2) -> p1.getAge() - p2.getAge() > 0 ? 1 : -1);
        //匿名内部类
        TreeSet<Person> treeSet2 = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getAge() - p2.getAge() > 0 ? 1 : -1;
            }
        });
        treeSet.add(new Person("zhangsan", 18));
        treeSet.add(new Person("lisi", 19));
        treeSet.add(new Person("wangwu", 10));
        System.out.println(treeSet);
    }
}

class MyPerson implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge()-p2.getAge()>0?1:-1;
    }
}
