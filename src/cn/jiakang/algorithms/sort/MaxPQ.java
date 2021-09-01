package cn.jiakang.algorithms.sort;

public class MaxPQ extends JkSort {
    /**
     * 下沉方法
     *
     * @param arr   二叉堆数组
     * @param index 需要下沉的元素索引
     * @param N     二叉堆数组的长度
     */
    private static void sink(Comparable[] arr, int index, int N) {
        while (2 * index <= N) {
            int j = 2 * index; // j: 子节点中较大值的下标，默认为左子节点的下标
            if (j < N && less(arr[j], arr[j + 1])) j += 1; // j + 1（右子节点）有元素，且比左子节点大，则jj指向右子节点
            if (!less(arr[index], arr[j])) break; // 比较k与max(子节点)的大小，若k较大则k已在合适的位置
            exch(arr, index, j); // k小于其子节点的较大值，交换k与j的指向
            index = j; // 将k的最新位置（原k的子节点位置）与其最新的子节点（原k的孙子节点）比较
        }
    }

    @Override
/**
 * 步骤描述
 * 1. 二叉堆有序化
 * 2. 将最大的元素与队尾元素交换
 * 3. 将队尾元素排除在二叉堆数组外
 * 4. 将队首元素下沉
 * 5. 重复步骤2直到所有元素完成交换
 */
    public void sort(Comparable[] arr) {
        int N = arr.length - 1; // 排除索引为0的元素
        // 二叉堆有序化；从后向前进行：大小为1的子堆不需要下沉
        for (int index = N / 2; index >= 1; index--) {
            sink(arr, index, N);
        }
        while (N > 1) {
            // 数组大小为2（0位置无元素）时，不需要进行处理
            exch(arr, 1, N--); // 将最大的元素与队尾元素交换 & 将队尾元素排除在二叉堆数组外
            sink(arr, 1, N); // 将队首元素下沉
        }
    }
}
