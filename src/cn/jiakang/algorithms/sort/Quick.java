package cn.jiakang.algorithms.sort;

public class Quick extends JkSort {
    /**
     * 步骤描述
     * 1. 获取切分元素
     * 2. 从左到右获取第一个比切分元素大的元素
     * 3. 从右到左获取第一个比切分元素小的元素
     * 4. 将2与3中的元素对调；并重复2/3/4直到完成所有比较
     * 5. 将切分元素放至对应的位置
     */
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
     * 1. 获取切分元素所处的位置
     * 2. 将数组按切分元素的位置切分为两端，递归调用快速排序
     */
    private static void quick(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;
        // if (hi <= lo + Insertion.M) { Insertion.sort(arr, lo, hi); return; } // 对于小数组，插入排序比快速排序快；M取值范围：5～15
        int j = partition(arr, lo, hi);
        quick(arr, lo, j - 1);
        quick(arr, j + 1, hi);
    }

    /**
     * 三向切分的快速排序
     */
    private static void quick3Way(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi, i = lo + 1;
        Comparable v = arr[lo]; // 切分元素
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp > 0) exch(arr, i, gt--); // i指向的元素大于切分元素，将位置i与位置gt的元素互换
            else if (cmp < 0) exch(arr, lt++, i++); // i指向的元素小于切分元素，将位置i与位置lt的元素互换
            else i++; // i指向的元素等于切分元素，判断下一个位置的元素
        }
        quick3Way(arr, lo, lt - 1);
        quick3Way(arr, gt + 1, hi);
    }

    @Override
    public void sort(Comparable[] arr) {
        quick(arr, 0, arr.length - 1);
        quick3Way(arr, 0, arr.length - 1);
    }
}
