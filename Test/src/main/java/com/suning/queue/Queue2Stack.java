package com.suning.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author lynn
 * @Date 2020/9/9 09:15
 */
public class Queue2Stack {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(int node) {
        queue1.add(node);
    }

    public int pop() {
        if (queue1.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        while (queue1.size()!=1){
            queue2.add(queue1.poll());
        }
        //将最后一个元素poll出去,此时queue1已经是空了
        int res = queue1.poll();
        swap(queue1,queue2);
        return res;
    }

    private void swap(Queue<Integer> queue1, Queue<Integer> queue2) {
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    public static void main(String[] args) {
        Queue2Stack queue2Stack = new Queue2Stack();
        queue2Stack.push(1);
        queue2Stack.push(2);
        queue2Stack.push(10);
        System.out.println(queue2Stack.pop());
        //queue2Stack.push(12);
        System.out.println(queue2Stack.pop());
    }
}
