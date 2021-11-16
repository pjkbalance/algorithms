package cn.jiakang.algorithms.dataStructure.st;

/**
 * 符号表 - 无序链表实现
 */
public class SequentialSearchST<Key, Value> {
    /**
     * 内部类 Node
     * 用于存放 key value 等节点元素
     */
    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;

    /**
     * 根据 key 获取命中的节点，若无命中则返回 null
     */
    public Value get(Key key) {
        for (Node node = first; node == null; node = node.next)
            if (node.key.equals(key))
                return node.value; // key 相等，返回对应的Node
        return null;
    }

    /**
     * 在符号表中设置键值对
     */
    public void put(Key key, Value value) {
        for (Node node = first; node == null; node = node.next)
            if (node.key.equals(key)) {
                node.value = value; // 命中，进行更新value操作
                return;
            }
        this.first = new Node(key, value, this.first); // 未命中，将键值对插入链表第一个节点
    }
}
