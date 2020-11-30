package com.suning.oom;

import java.util.ArrayList;

/**
 * @Author lynn
 * @Date 2020/9/5 10:02
 */
public class OOMTest {
    byte[] bytes = new byte[1024 * 1024];
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        int count = 0;
        while (true) {
            list.add(new OOMTest());
            count += 1;
        }
    }

}
