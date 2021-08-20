package cn.jiakang.algorithms.sort;

public class Insertion extends JkSort {
    /**
     * 步骤描述
     * 1. 获取第i个元素（1 ～ N-1），将该元素与该元素之前的元素从后往前逐一比较
     * 2.1 若不符合排序规则，则将对应元素互换并继续比较
     * 2.2 若符合排序规则，则判断该元素已插入到正确位置并且不再进行之后的比较
     * 3. 如此往复，直到整个数组排序完成
     */
    @Override
    public void sort(Comparable[] arr) {
        int compareCount = 0, exchCount = 0;
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            // 将arr[i]插入arr[0]...arr[i-1],arr[i-2]中
            for (int j = i; j > 0; j--) {
                // 比较次数：最优情况：1次 共N-1次；最查情况：i次 共1+2+...+(N-1)次
                compareCount++;
                if (less(arr[j - 1], arr[j])) break;
                // 转换次数：最优情况：0次 共0次；最差情况：i次 共1+2+...+(N-1)次
                exchCount++;
                exch(arr, j, j - 1);
            }
        }
        System.out.println("compare count: " + compareCount + " exchange count: " + exchCount);
    }
}
