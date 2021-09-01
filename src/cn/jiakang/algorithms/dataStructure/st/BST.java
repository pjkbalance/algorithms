package cn.jiakang.algorithms.dataStructure.st;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 符号表 - 二叉查找树
 */
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N; // 该结点为根的子树中结点的总数(包括该结点)

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key); // key 小于该结点的key
        if (cmp > 0) return get(node.right, key); // key 大于该结点的key
        return node.value; // key 等于该结点的key
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1); // 结点为 null 生成一个新的结点（插入新结点）
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value); // key 小于该结点的key，更新左结点
        else if (cmp > 0) node.right = put(node.right, key, value); // key 大于该结点的key，更新右结点
        else node.value = value; // key 等于该结点的key，更新该结点的值
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key min() {
        Node node = min(root);
        return node.key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key max() {
        Node node = max(root);
        return node.key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return floor(node.left, key);
        else if (cmp == 0) return node;
        else {
            Node tmp = floor(node.right, key);
            return tmp == null ? node : tmp;
        }
    }

    public Key select(int index) {
        return select(root, index).key;
    }

    private Node select(Node node, int index) {
        if (node == null) return null;
        int tmp = size(node.left);
        if (tmp > index) return select(node.left, index); // 左结点个数 > 查询索引，对应结点在左子树
        if (tmp < index)
            // 左结点个数 < 查询索引，对应结点在右子树；搜索右子树并重置索引，索引 - 父结点左子树个数 - 父节点所占个数(1)
            return select(node.right, index - tmp - 1);
        return node; // 左结点个数 == 查询索引，返回当前结点
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node.left, key); // 键在左子数
        else if (cmp > 0) return size(node.left) + 1 + rank(node.right, key); // 键在右子数
        else return size(node.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right; // 最左边的（最小）结点，返回该结点的右兄弟结点
        node.left = deleteMin(node.left); // 未找到最小节点，迭代删除左子树的最小结点
        node.N = size(node.left) + size(node.right) + 1; // 更新结点个数
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key); // 在左子树中删除对应结点
        else if (cmp > 0) node.right = delete(node.right, key); // 在右子树中删除对应结点
        else {
            // 匹配到要删除的节点
            if (node.left == null) return node.right; // 若没有比要删除的节点小的，则直接用右结点替代该结点
            if (node.right == null) return node.left; // 若没有比要删除的节点大的，则直接用左结点替代该结点
            Node tmp = node; // 将待删除节点缓存
            node = min(tmp.right); // 将删除结点的指针指向右子树最小节点
            node.right = deleteMin(tmp.right); // 将右子树的最小结点删除并进行整理作为新的右子树
            node.left = tmp.left; // 将原左子树作为新的左子树
        }
        node.N = size(node.left) + 1 + size(node.right); // 更新结点个数
        return node;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new PriorityQueue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
        if (cmplo <= 0 && 0 <= cmphi) queue.add(node.key);
    }

//    public Node ceiling(Key key) {}
//    public Node deleteMax() {}
//    public Node keys() {}
}
