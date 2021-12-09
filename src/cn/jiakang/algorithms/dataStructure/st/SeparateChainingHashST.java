package cn.jiakang.algorithms.dataStructure.st;

public class SeparateChainingHashST<Key, Value> {
private int N; // 键值对总数
private int M; // 散列表的大小
private SequentialSearchST<Key, Value>[] st;

public SeparateChainingHashST() {
    this(997);
}

public SeparateChainingHashST(int m) {
    this.M = m;
    st = new SequentialSearchST[M];
    for (int i = 0; i < M; i++) {
        st[i] = new SequentialSearchST<>();
    }
}

private int hash(Key key) {
    return (key.hashCode() & 0X7fffffff) % M;
}

private Value get(Key key) {
    return st[hash(key)].get(key);
}

private void put(Key key, Value value) {
    st[hash(key)].put(key, value);
}
}
