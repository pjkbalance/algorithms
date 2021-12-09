package cn.jiakang.algorithms.dataStructure.st;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 反向索引
 */
public class LookupIndex {
    public static void main(String... args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String sp = args[1]; // 分隔符
        LinearProbingHashST<String, Queue<String>> st = new LinearProbingHashST<>();
        LinearProbingHashST<String, Queue<String>> ts = new LinearProbingHashST<>();
        while (in.ready()) {
            String lineStr = in.readLine();
            String[] strArr = lineStr.split(sp);
            String key = strArr[0];
            for (int i = 1; i < strArr.length; i++) {
                String str = strArr[i];
                if (!st.contains(key)) {
                    st.put(key, new ConcurrentLinkedQueue<>());
                }
                if (!ts.contains(str)) {
                    ts.put(str, new ConcurrentLinkedQueue<>());
                }
                st.get(key).add(str);
                ts.get(str).add(key);
            }
        }
        in.close();
    }
}
