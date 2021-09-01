package cn.jiakang.algorithms.dataStructure.st;

import java.time.chrono.IsoEra;

/**
 * 符号表 - 红黑树
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color == RED;
    }

    private Node root;

    private int size(Node node) {
        return node.N;
    }

    /**
     * 左旋转
     * |    b(leftNode)           c
     * |   / \                   / \
     * |  a   c(rightNode)  ->  b   e
     * |     / \               / \
     * |    d   e             a   d
     */
    private Node rotateLeft(Node leftNode) {
        // 规整结点位置
        Node rightNode = leftNode.right;
        leftNode.right = rightNode.left;
        rightNode.left = leftNode;
        // 将原根节点的颜色与结点个数赋值给新的根节点
        rightNode.color = leftNode.color;
        rightNode.N = leftNode.N;
        // 设置子结点颜色与结点个数
        leftNode.color = RED;
        leftNode.N = size(leftNode.left) + size(leftNode.right) + 1;
        return rightNode;
    }

    /**
     * 右旋转
     * |             c(rightNode)      b
     * |            / \               / \
     * | (leftNode)b   e         ->  a   c
     * |          / \                   / \
     * |         a   d                 d   e
     */
    private Node rotateRight(Node rightNode) {
        // 规整结点位置
        Node leftNode = rightNode.left;
        rightNode.left = leftNode.right;
        leftNode.right = rightNode;
        // 将原根节点的颜色与结点个数赋值给新的根节点
        leftNode.color = rightNode.color;
        leftNode.N = rightNode.N;
        // 设置子结点颜色与结点个数
        rightNode.color = RED;
        rightNode.N = size(rightNode.left) + size(rightNode.right) + 1;
        return leftNode;
    }

    /**
     * 着色
     * 4-结点拆分
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        // 插入新元素，均为红链接；在父结点的判断在中若最新红链接未满足红黑树要求，则进行旋转
        if (node == null) return new Node(key, value, 1, RED);
        // 判断插入/更新结点的位置
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        // 判断是否需要旋转
        if (isRed(node.right) && !isRed(node.left))
            // 2-结点较小值作为父结点，需要左旋转使较大值作为父结点（保证只有左链接会出现红链接的情况）
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            // 连续两个红链接，则该结点为4-结点，需要左旋转生成一个2树结构，为拆分作准备
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            // 若该结点为4-结点，则重置结点颜色使二叉树长高。中间键上移到父结点进入下一轮判断看是否生成新的4-结点
            flipColors(node);
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    private Node balance(Node node) {
        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node); // 左链接为黑链接，右链接为红链接；左旋转
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node); // 左链接为两个连续的红链接；右旋转（包括上一步中的临时4-结点，长高成为一个两层的树）
        if (isRed(node.left) && isRed(node.right)) flipColors(node); // 左右链接均为红链接；将红链接上移

        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (root.N != 0) root.color = BLACK;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (root.N != 0) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null; // node 为最小值，直接删除
        if (!isRed(node.left) && !isRed(node.left.left)) node = moveRedLeft(node); // 从上至下 将 2-结点 变为 3-结点 / 3-结点 变为 4-结点
        node.left = deleteMin(node.left); // 迭代 处理左子树
        return balance(node); // 从下至上 整理红黑树
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left)) node = rotateRight(node);
        if (node.right == null) return null; // node 为最大值，直接删除
        if (!isRed(node.right) && !isRed(node.right.left))
            node = moveRedRight(node); // 从上至下 将 2-结点 变为 3-结点 / 3-结点 变为 4-结点
        node.right = deleteMax(node.right); // 迭代 处理左子树
        return balance(node); // 从下至上 整理红黑树
    }

    private Node moveRedLeft(Node node) {
        flipColors(node); // 将根节点上移至父结点，对父结点：2-结点 变为 3-结点 / 3-结点 变为 4-结点
        if (isRed(node.left.left)) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node); // 将根节点上移至父结点，对父结点：2-结点 变为 3-结点 / 3-结点 变为 4-结点
        if (isRed(node.right.left)) {
            // 兄弟结点的链接为3-结点，则通过旋转变为4-结点
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (root.N != 0) root.color = BLACK;
    }

    private Node delete(Node node, Key key) {
        if (key.compareTo(node.key) < 0) {
            // 需要删除的节点在左子树上
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        }
        return null;
    }

}
