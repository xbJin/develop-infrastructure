package com.develop.infrastructure.leetcode.leet200.leet146;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;
        
        Node() {
            this(null, null);
        }
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final Node<K, V> head; // 虚拟头节点
    private final Node<K, V> tail; // 虚拟尾节点
    
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量必须大于0");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // 使用无参构造函数创建虚拟节点
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("key不能为null");
        }
        
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 移动到链表头部表示最近使用
        moveToHead(node);
        return node.value;
    }
    
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key和value不能为null");
        }
        
        Node<K, V> node = cache.get(key);
        if (node == null) {
            // 创建新节点
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            
            if (cache.size() > capacity) {
                // 删除最久未使用的节点
                Node<K, V> removed = removeTail();
                if (removed != null) {
                    cache.remove(removed.key);
                }
            }
        } else {
            // 更新值并移动到头部
            node.value = value;
            moveToHead(node);
        }
    }
    
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
    
    public int size() {
        return cache.size();
    }
    
    public void clear() {
        cache.clear();
        // 重置链表
        head.next = tail;
        tail.prev = head;
    }
    
    public V remove(K key) {
        Node<K, V> node = cache.remove(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        return node.value;
    }
    
    // ========== 私有方法 ==========
    
    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        // 帮助GC
        node.prev = null;
        node.next = null;
    }
    
    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }
    
    private Node<K, V> removeTail() {
        if (tail.prev == head) {
            return null;  // 缓存为空
        }
        Node<K, V> res = tail.prev;
        removeNode(res);
        return res;
    }
    
    // ========== 辅助方法 ==========
    
    /**
     * 打印缓存内容（调试用）
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