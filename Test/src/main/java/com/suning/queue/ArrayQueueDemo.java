package com.suning.queue;

/**
 * @Author lynn
 * @Date 2020/9/9 12:49
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.addQueue(5);
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        queue.addQueue(1);//队列已经满了,添加不进去了
        queue.addQueue(2);
    }
}

/**
 * 将尾指针往后移：rear+1 , 当front == rear 【空】
 * 若尾指针 rear 小于队列的最大下标 maxSize-1，
 * 则将数据存入 rear所指的数组元素中，否则无法存入数据。
 * rear  == maxSize - 1[队列满]
 */
class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;
    /**构造方法的好处是可以外面传参数*/
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }
    /** 判断是否为空*/
    public boolean isEmpty(){
        return front == rear;//front和rear相等,都等于-1,那么就是为空
    }
    /** 判断是否满了*/
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    /**向队列添加数据*/
    public void addQueue(int num){
        if (isFull()) {//如果满了,那么就不会再添加
            System.out.println("队列已经满了,不能再添加数据");
            return;
        }
        rear ++;//rear向后移动,增加数据的时候,front不动,rear向后移动
        arr[rear] = num;
    }
    /**获取队列的数据,出队列*/
    public int getQueue() {
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能获取数据");//默认有return,后面的不会执行
        }
        front ++;//front后移,数组arr不变[0,maxSize-1],但是队列的区间在变,[front,rear]
        return arr[front];//
    }
    /** 遍历队列的所有数据*/
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    /**显示队列头信息*/
    public int headQueue(){
        //非空判断
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }


}


