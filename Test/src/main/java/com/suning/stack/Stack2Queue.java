package com.suning.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author lynn
 * @Date 2020/9/7 17:09
 */
public class Stack2Queue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void add(int node){
        stack1.push(node);
    }
    public int poll(){
        //先将stack1的元素全部放到stack2
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        //stack2的元素相当于已经顺序换过来了
        return stack2.pop();
    }
    public static void main(String[] args) {
        Stack2Queue stack2Queue = new Stack2Queue();
        stack2Queue.add(1);
        stack2Queue.add(2);
        stack2Queue.add(3);
        System.out.println(stack2Queue.poll());
        stack2Queue.add(4);
        System.out.println(stack2Queue.poll());
        System.out.println(stack2Queue.poll());
        System.out.println(stack2Queue.poll());
        /**下面是对linkedlist的测试*/
        List<Integer> list = new LinkedList<Integer>();
        list.add(5);
        System.out.println(list.get(0));
    }
}
