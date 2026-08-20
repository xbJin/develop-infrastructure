package com.develop.infrastructure.leetcode.leet200.leet146;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LRU (Least Recently Used) 缓存实现类
 * <p>
 * 采用双向链表 + HashMap 的经典设计方案，实现 O(1) 时间复杂度的 get 和 put 操作。
 * 双向链表用于维护访问顺序：头部为最近使用的节点，尾部为最久未使用的节点。
 * HashMap 用于快速定位节点，key 为缓存键，value 为对应的链表节点。
 * <p>
 * 核心特性：
 * - 当缓存满时，自动淘汰最久未使用的元素
 * - 访问（get）或更新（put）元素时，将其移到链表头部
 * - 支持泛型，可存储任意类型的键值对
 * - 线程不安全，如需线程安全请使用 ConcurrentLRUCache
 *
 * @param <K> 键的类型
 * @param <V> 值的类型
 */
public class LRUCache<K, V> {

    /**
     * 双向链表节点内部类
     * <p>
     * 每个节点存储键、值以及前后指针，用于维护链表结构。
     *
     * @param <K> 键的类型
     * @param <V> 值的类型
     */
    private static class Node<K, V> {
        /** 缓存键 */
        K key;
        /** 缓存值 */
        V value;
        /** 前驱节点指针 */
        Node<K, V> prev;
        /** 后继节点指针 */
        Node<K, V> next;

        /**
         * 无参构造函数，创建空节点（用于虚拟头尾节点）
         */
        Node() {
            this(null, null);
        }

        /**
         * 带参数构造函数，创建包含键值的节点
         *
         * @param key   缓存键
         * @param value 缓存值
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 缓存容量，即最多可存储的键值对数量
     */
    private final int capacity;

    /**
     * HashMap 用于快速查找节点，O(1) 时间复杂度
     * key: 缓存键，value: 对应的链表节点
     */
    private final Map<K, Node<K, V>> cache;

    /**
     * 虚拟头节点（哨兵节点）
     * <p>
     * 简化边界处理，头节点的 next 指向真正的第一个节点
     */
    private final Node<K, V> head;

    /**
     * 虚拟尾节点（哨兵节点）
     * <p>
     * 简化边界处理，尾节点的 prev 指向真正的最后一个节点
     */
    private final Node<K, V> tail;

    /**
     * 构造函数，初始化 LRU 缓存
     *
     * @param capacity 缓存容量，必须大于 0
     * @throws IllegalArgumentException 如果容量小于等于 0
     */
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量必须大于0");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // 初始化虚拟头尾节点，并建立连接
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取缓存中指定键的值
     * <p>
     * 如果键存在，将其移到链表头部表示最近使用；如果不存在，返回 null。
     *
     * @param key 要获取的键，不能为 null
     * @return 键对应的值，如果不存在则返回 null
     * @throws NullPointerException 如果 key 为 null
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("key不能为null");
        }

        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 将访问的节点移到链表头部，表示最近使用
        moveToHead(node);
        return node.value;
    }

    /**
     * 向缓存中存入键值对
     * <p>
     * 如果键已存在，更新值并移到链表头部；如果键不存在，创建新节点并添加到头部。
     * 如果缓存已满，删除最久未使用的节点（链表尾部）。
     *
     * @param key   要存入的键，不能为 null
     * @param value 要存入的值，不能为 null
     * @throws NullPointerException 如果 key 或 value 为 null
     */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key和value不能为null");
        }

        Node<K, V> node = cache.get(key);
        if (node == null) {
            // 键不存在，创建新节点
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            // 检查是否超过容量，若超过则删除最久未使用的节点
            if (cache.size() > capacity) {
                Node<K, V> removed = removeTail();
                if (removed != null) {
                    cache.remove(removed.key);
                }
            }
        } else {
            // 键已存在，更新值并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 判断缓存中是否包含指定的键
     *
     * @param key 要检查的键
     * @return 如果包含返回 true，否则返回 false
     */
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    /**
     * 获取当前缓存中的键值对数量
     *
     * @return 当前缓存大小
     */
    public int size() {
        return cache.size();
    }

    /**
     * 清空缓存中的所有键值对
     * <p>
     * 清空 HashMap 并重置链表结构
     */
    public void clear() {
        cache.clear();
        // 重置链表，仅保留虚拟头尾节点
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 从缓存中删除指定键的键值对
     *
     * @param key 要删除的键
     * @return 被删除的值，如果键不存在返回 null
     */
    public V remove(K key) {
        Node<K, V> node = cache.remove(key);
        if (node == null) {
            return null;
        }
        // 从链表中移除该节点
        removeNode(node);
        return node.value;
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 将节点添加到链表头部
     * <p>
     * 操作顺序：
     * 1. 设置新节点的前驱为 head
     * 2. 设置新节点的后继为 head.next
     * 3. 更新原 head.next 的前驱为新节点
     * 4. 更新 head.next 为新节点
     *
     * @param node 要添加的节点
     */
    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 从链表中移除指定节点
     * <p>
     * 操作顺序：
     * 1. 将前驱节点的后继指向当前节点的后继
     * 2. 将后继节点的前驱指向当前节点的前驱
     * 3. 清空当前节点的前后指针，帮助 GC 回收
     *
     * @param node 要移除的节点
     */
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // 清空指针，帮助 GC 回收
        node.prev = null;
        node.next = null;
    }

    /**
     * 将节点移动到链表头部
     * <p>
     * 先从链表中移除该节点，再将其添加到头部
     *
     * @param node 要移动的节点
     */
    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 移除链表尾部节点（最久未使用的节点）
     *
     * @return 被移除的节点，如果链表为空返回 null
     */
    private Node<K, V> removeTail() {
        if (tail.prev == head) {
            return null;  // 缓存为空
        }
        Node<K, V> res = tail.prev;
        removeNode(res);
        return res;
    }

    // ==================== 调试辅助方法 ====================

    /**
     * 打印缓存内容（调试用）
     * <p>
     * 输出格式：Cache [最近使用 → 最久未使用]: {key1=value1} {key2=value2} ...
     */
    public void printCache() {
        System.out.print("Cache [最近使用 → 最久未使用]: ");
        Node<K, V> current = head.next;
        while (current != tail) {
            System.out.print("{" + current.key + "=" + current.value + "} ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 按访问顺序返回所有键
     * <p>
     * 返回的顺序为：最近使用的键在前，最久未使用的键在后
     *
     * @return 键的可迭代对象
     */
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        Node<K, V> current = head.next;
        while (current != tail) {
            keys.add(current.key);
            current = current.next;
        }
        return keys;
    }
}