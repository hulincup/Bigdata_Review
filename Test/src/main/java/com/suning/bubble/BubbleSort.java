package com.suning.bubble;

/**
 * @Author lynn
 * @Date 2020/9/3 20:12
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 2, 5, 4, 6, 7, 9, 8, 0};
        bubbleSort(ints);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    /**
     * 冒泡排序算法
     * @param array
     */
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }

    }

}
