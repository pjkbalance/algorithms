package cn.jiakang.algorithms.sort;

public class Shell extends JkSort {
/**
 * 步骤描述
 * 1. 按一定规则生成h
 * 2. 使用插入排序算法，生成h有序数组
 * 3. 按规则缩小h的值
 * 4. 重复第二步，直到整个数组排序完成
 */
    @Override
    public void sort(Comparable[] arr) {
    int N = arr.length, h = 1;
    while (h < N / 3) h = 3 * h + 1; // 递增序列：从1开始逐渐增加
    while (h >= 1) {
        // 对数组排序生成 h有序数组，直到h为1
        for (int i = h; i < N; i++) {
            for (int j = i; j >= h; j -= h) {
                if (less(arr[j - h], arr[j])) break;
                exch(arr, j, j - h);
            }
        }
        h /= 3;
    }
}
}