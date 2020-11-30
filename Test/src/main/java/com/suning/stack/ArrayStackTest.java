package com.suning.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lynn
 * @Date 2020/9/9 21:40
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("第一次弹栈的是："+stack.pop());
        stack.list();
        Map hashMap = new HashMap<String,Integer>();
        ArrayList<Object> list = new ArrayList<>();
        list.add("hello");
        list.add(1);


    }
}

class ArrayStack {
    private int maxSize;//栈的最大空间
    private int[] stack;//定义一个栈，用数组来模拟
    private int top = -1;//栈顶的初始值

    //构造器 主要作用是可以传参数maxSize
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = num;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        //栈的遍历要注意：是top--,
        for (int i = top; i > -1; i--) {
            System.out.printf("第%d个出栈的是：%d", i + 1, stack[i]);
            System.out.println();
        }

    }
}
