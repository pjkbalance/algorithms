package cn.jiakang.algorithms.dataStructure.st;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取键值对
     */
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    /**
     * 插入键值对，忽略了扩展数组的代码
     */
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            // 命中，进行更新value操作
            values[i] = value;
            return;
        }
        // 未命中
        for (int index = N; index > i; index--) {
            // 将后续数组向后移动给需要插入的元素腾出位置
            keys[index] = keys[index - 1];
            values[index] = values[index - 1];
        }
        // 将元素插入对应位置
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2, cmp = keys[mid].compareTo(key);
            if (cmp > 0) hi = mid - 1;
            else if (cmp < 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
}
