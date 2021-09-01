package cn.jiakang.algorithms.dataStructure;

/**
 * 优先队列
 * 二叉堆实现
 */
public class MaxPQ<T extends Comparable<T>> {
    private T[] arr;
    private int N = 0;

    public MaxPQ(int maxN) {
        this.arr = (T[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T v) {
        this.arr[++N] = v;
        swim(N);
    }

    public T delMax() {
        T max = this.arr[1];
        exch(1, N);
        this.arr[N--] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void exch(int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 上浮：k节点的优先级上升
     * 通过交换 k 与 k的父节点 来修复堆
     * 从下至上恢复堆的顺序
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            // k 未达到根节点，并且父节点比k指向的值小
            exch(k, k / 2); // 父节点与k节点互换
            k /= 2; // 将元素的最新位置（k的父节点）与其最新的父节点（k的祖父节点）比较；
        }
    }

    /**
     * 下沉：k节点的优先级下降
     * 通过交换 k 与 max(k的子节点) 来修复堆
     * 从上至下恢复堆的顺序
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k; // j: 子节点中较大值的下标，默认为左子节点的下标
            if (j < N && less(j, j + 1)) j += 1; // j + 1（右子节点）有元素，且比左子节点大，则jj指向右子节点
            if (!less(k, j)) break; // 比较k与max(子节点)的大小，若k较大则k已在合适的位置
            exch(k, j); // k小于其子节点的较大值，交换k与j的指向
            k = j; // 将k的最新位置（原k的子节点位置）与其最新的子节点（原k的孙子节点）比较
        }
    }
}
