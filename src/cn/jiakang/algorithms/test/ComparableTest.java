package cn.jiakang.algorithms.test;

import java.util.Comparator;

public class ComparableTest implements Comparator<String>, Comparable<String> {
    @Override
    public int compare(String o1, String o2) {
        return 0;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
