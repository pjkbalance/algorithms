package cn.jiakang.algorithms.dataStructure.st;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SparseVector {
    private Map<Integer, Double> st;

    public SparseVector() {
        st = new HashMap<>();
    }

    public int size() {
        return st.size();
    }

    public void put(int key, double value) {
        st.put(key, value);
    }

    public double get(int key) {
        if (st.containsKey(key)) return st.get(key);
        else return 0.0;
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keySet()) {
            sum += that[i] * get(i);
        }
        return sum;
    }
}
