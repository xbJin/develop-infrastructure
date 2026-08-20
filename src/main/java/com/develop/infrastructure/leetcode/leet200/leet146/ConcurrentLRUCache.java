package com.develop.infrastructure.leetcode.leet200.leet146;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCache<K, V> {
    private final LRUCache<K, V> cache;
    private final Lock lock = new ReentrantLock();
    
    public ConcurrentLRUCache(int capacity) {
        this.cache = new LRUCache<>(capacity);
    }
    
    public V get(K key) {
        lock.lock();
        try {
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }
    
    public void put(K key, V value) {
        lock.lock();
        try {
            cache.put(key, value);
        } finally {
            lock.unlock();
        }
    }
    
    public boolean containsKey(K key) {
        lock.lock();
        try {
            return cache.containsKey(key);
        } finally {
            lock.unlock();
        }
    }
    
    public int size() {
        lock.lock();
        try {
            return cache.size();
        } finally {
            lock.unlock();
        }
    }
    
    public void clear() {
        lock.lock();
        try {
            cache.clear();
        } finally {
            lock.unlock();
        }
    }
    
    public V remove(K key) {
        lock.lock();
        try {
            return cache.remove(key);
        } finally {
            lock.unlock();
        }
    }
    
    public void printCache() {
        lock.lock();
        try {
            cache.printCache();
        } finally {
            lock.unlock();
        }
    }
    
    public Iterable<K> keys() {
        lock.lock();
        try {
            return cache.keys();
        } finally {
            lock.unlock();
        }
    }
}