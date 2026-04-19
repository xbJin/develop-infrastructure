package com.develop.infrastructure.leetcode.leet200.leet146;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCache<K, V> extends LRUCache<K, V> {
    private final Lock lock = new ReentrantLock();
    
    public ConcurrentLRUCache(int capacity) {
        super(capacity);
    }
    
    @Override
    public V get(K key) {
        lock.lock();
        try {
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void put(K key, V value) {
        lock.lock();
        try {
            super.put(key, value);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public boolean containsKey(K key) {
        lock.lock();
        try {
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public V remove(K key) {
        lock.lock();
        try {
            return super.remove(key);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void clear() {
        lock.lock();
        try {
            super.clear();
        } finally {
            lock.unlock();
        }
    }
}