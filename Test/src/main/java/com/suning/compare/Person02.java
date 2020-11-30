package com.suning.compare;

/**
 * @Author lynn
 * @Date 2020/9/3 19:54
 */
public class Person02 implements Comparable<Person02> {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person02() {
    }

    public Person02(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person02 p) {
        return this.age - p.getAge() > 0 ? 1 : -1;
    }
}
