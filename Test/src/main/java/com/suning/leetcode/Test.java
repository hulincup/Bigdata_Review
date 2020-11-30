package com.suning.leetcode;

import java.util.ArrayList;
import java.util.List;

class RepeatArray {
    public List<Integer> findRepeatNumber(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int count = 0;
            for(int j=0;j<nums.length;j++){
                if(nums[j]==nums[i]){
                    count++;
                }
            }
            if(count>1 && !list.contains(nums[i])){
                list.add(nums[i]);
            }
        }
        return list;
    }
}
public class Test {
    public static void main(String[] args) {
        RepeatArray repeatArray = new RepeatArray();
        int[] arr = new int[]{1,2,2,3,4,5,5};
        List<Integer> repeatNumbers = repeatArray.findRepeatNumber(arr);
        for (int repeatNumber : repeatNumbers) {
            System.out.println(repeatNumber);
        }
    }
}