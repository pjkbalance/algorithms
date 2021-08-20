package cn.jiakang.algorithms.sort;

public class Selection extends JkSort {
    /**
     * 步骤描述
     * 1. 找到数组中最小的元素并将其与数组第一个元素交换
     * 2. 在剩下的元素中找到最小的元素并将其与数组中第二个元素交换
     * 3. 如此往复，直到整个数组排序完成
     */
    public void sort(Comparable[] arr) {
        int compareCount = 0, exchCount = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) { // 循环N次
            int minIndex = i; // 开始第i+1次比较，最小值下标初始值为i
            for (int j = i + 1; j < N; j++) { // 第i次比较，需要比较N-1-i次
                compareCount++;
                if (less(arr[j], arr[minIndex])) {
                    // 下标j的值小于当前记录的最小值，更新最小值下标
                    minIndex = j;
                }
            }
            exch(arr, i, minIndex); // 数据移动N次
            exchCount++;
        }
        System.out.println("compare count: " + compareCount + " exchange count: " + exchCount);
    }
}
