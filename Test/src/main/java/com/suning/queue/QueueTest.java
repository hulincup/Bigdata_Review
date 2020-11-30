package com.suning.queue;

import java.util.Queue;

/**
 * @Author lynn
 * @Date 2020/9/7 19:06
 */
public abstract  class QueueTest implements Queue<Integer> {

    @Override
    public boolean add(Integer integer) {
        return false;
    }

    @Override
    public boolean offer(Integer integer) {
        return false;
    }

    @Override
    public Integer remove() {
        return null;
    }

    @Override
    public Integer poll() {
        return null;
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public Integer peek() {
        return null;
    }
}
