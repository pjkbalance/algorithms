package cn.jiakang.algorithms.sort;

public class Quick extends JkSort {
    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = arr[lo]; // 切分元素
        while (true) {
            while (less(arr[++i], v)) // 从左至右找到第一个比切分元素大的元素
                if (i == hi) break; // 所有元素均小于切分元素
            while (less(v, arr[--j])) // 从右至左找到第一个比切分元素小的元素
                if (j == lo) break; // 所有元素均大于切分元素
            if (i >= j) break; // 已对数组中所有元素进行判断
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    /**
     * 步骤描述
     * 1.
     */
    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    @Override
    public void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
