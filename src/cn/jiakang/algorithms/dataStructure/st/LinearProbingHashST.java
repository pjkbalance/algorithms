package cn.jiakang.algorithms.dataStructure.st;

public class LinearProbingHashST<Key, Value> {
private int N; // 键值对的总数
private int M = 16;// 线性探测表的大小
private Key[] keys;
private Value[] values;

public LinearProbingHashST() {
    keys = (Key[]) new Object[M];
    values = (Value[]) new Object[M];
}

public LinearProbingHashST(int cap) {
    keys = (Key[]) new Object[cap];
    values = (Value[]) new Object[cap];
}

private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
}

private void resize(int cap) {
    LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
    for (int i = 0; i < M; i++) {
        if (keys[i] != null) {
            t.put(keys[i], values[i]);
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }
}

public void put(Key key, Value value) {
    if (N >= M / 2) resize(M * 2);
    int i;
    for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
        if (keys[i].equals(key)) {
            values[i] = value;
            return;
        }
    }
    keys[i] = key;
    values[i] = value;
    N++;
}

public Value get(Key key) {
    for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
        if (keys[i].equals(key)) {
            return values[i];
        }
    }
    return null;
}

public boolean contains(Key key) {
    return get(key) != null;
}

public void delete(Key key) {
    if (!contains(key)) return;
    int i = hash(key);
    while (!key.equals(keys[i])) i = (i + 1) % M;
    keys[i] = null;
    values[i] = null;
    // 处理后续键簇
    i = (i + 1) % M;
    while (keys[i] != null) {
        Key keyToRedo = keys[i];
        Value valueToRedo = values[i];
        keys[i] = null;
        values[i] = null;
        N--;
        put(keyToRedo, valueToRedo);
        i = (i + 1) % M;
    }
    N--;
    if (N > 0 && N == M / 8) resize(M / 2);
}

}
