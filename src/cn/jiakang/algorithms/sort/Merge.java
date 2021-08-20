package cn.jiakang.algorithms.sort;

public class Merge extends JkSort {
    private static Comparable[] tmpArr;

    /**
     * 原地归并抽象方法
     * 步骤描述
     * 1. 将数组复制到临时数组中
     * 2. 从头开始比较前半部分与后半部分未归并的第一个元素
     * 2.1 前半部分全部归并完成，则依次归并后半部分数组
     * 2.2 后半部分全部归并完成，则依次归并前半部分数组
     * 2.3 比较前半部分第一个未归并元素与后半部分第一个未归并元素，将较小的元素归并进新的数组
     *
     * @param arr 待归并的数组 [0...mid]/[mid+1...hi]为两个不同的有序数组
     * @param lo  数组前半部分的起始索引
     * @param mid 数组前半部分的结束索引，mid+1为数组后半部分的起始索引
     * @param hi  数组后半部分的结束索引
     */
    private static void merge(Comparable[] arr, int lo, int mid, int hi) {
        for (int index = lo; index <= hi; index++) tmpArr[index] = arr[index]; // step1
        int i = lo, j = mid + 1; // i：前半段数据索引；j：后半段数据索引
        for (int index = lo; index <= hi; index++) {
            // 将数据一次归并至第index个元素
            // 比较次数：最好情况：；最差情况
            if (i > mid) {
                // step2.1 前半段数组用尽，只需要将后半段未归并的元素依次添加
                arr[index] = tmpArr[j++];
            } else if (j > hi) {
                // step2.2 后半段数组用尽，只需要将前半段未归并的元素依次添加
                arr[index] = tmpArr[i++];
            } else if (less(tmpArr[i], tmpArr[j])) {
                // step2.3 索引i指向元素小于索引j指向元素，插入索引i指向的元素
                arr[index] = tmpArr[i++];
            } else {
                // step2.3 索引j指向元素小于/等于索引i指向元素，插入索引j指向的元素
                arr[index] = tmpArr[j++];
            }
        }
    }

    /**
     * 自顶而下归并排序
     * 步骤描述
     * 1. 将数组拆分成两半
     * 2. 当数组的长度到达1时，认为该数组已经排序完成
     * 3. 将两个数组进行归并排序
     * 4. 层层向下，直到排序完毕
     */
    private void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return; // 下标/上标指向同一个元素，不需要调用归并排序
        int mid = lo + (hi - lo) / 2; // 每次将数组分割为两部分，进行递归排序
        sort(arr, lo, mid); // 对前半部分排序
        sort(arr, mid + 1, hi); // 对后半部分排序
        merge(arr, lo, mid, hi); // 前/后两部分均排序完成，将其进行归并排序
    }

    /**
     * 自底向上归并排序，适合使用链表
     * 步骤描述
     * 1. 归并微型数组
     * 2. 成对归并得到的子数组
     * 3. 层层向上，直到排序完毕
     */
    private static void sortBU(Comparable[] arr) {
        int N = arr.length;
        for (int size = 1; size < N; size += size) {
            for (int lo = 0; lo < N - size; lo += size + size) {
                merge(arr, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
            }
        }
    }

    @Override
    public void sort(Comparable[] arr) {
        tmpArr = new Comparable[arr.length]; // 临时存放数组，额外的内存消耗
//        sort(arr, 0, arr.length - 1);
        sortBU(arr);
    }
}
