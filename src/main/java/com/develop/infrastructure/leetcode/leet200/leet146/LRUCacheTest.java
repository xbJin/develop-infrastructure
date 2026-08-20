package com.develop.infrastructure.leetcode.leet200.leet146;



public class LRUCacheTest {
    public static void main(String[] args) {
        testGenericCache();
        testEdgeCases();
        testKeysMethod();
        testConcurrentCache();
    }
    
    private static void testGenericCache() {
        System.out.println("=== 测试泛型LRU缓存 ===");
        
        LRUCache<Integer, String> cache1 = new LRUCache<>(3);
        cache1.put(1, "A");
        cache1.put(2, "B");
        cache1.put(3, "C");
        cache1.printCache();
        
        cache1.get(1);
        cache1.printCache();
        
        LRUCache<String, Integer> cache2 = new LRUCache<>(2);
        cache2.put("apple", 10);
        cache2.put("banana", 20);
        cache2.put("cherry", 30);
        System.out.println("banana: " + cache2.get("banana"));
        System.out.println("cherry: " + cache2.get("cherry"));
    }
    
    private static void testEdgeCases() {
        System.out.println("\n=== 测试边界情况 ===");
        
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, "One");
        System.out.println("get(1): " + cache.get(1));
        
        cache.put(2, "Two");
        System.out.println("get(1): " + cache.get(1));
        System.out.println("get(2): " + cache.get(2));
        
        try {
            cache.put(null, "test");
        } catch (NullPointerException e) {
            System.out.println("正确捕获null key: " + e.getMessage());
        }
        
        try {
            cache.put(3, null);
        } catch (NullPointerException e) {
            System.out.println("正确捕获null value: " + e.getMessage());
        }
        
        try {
            new LRUCache<>(0);
        } catch (IllegalArgumentException e) {
            System.out.println("正确捕获非法容量: " + e.getMessage());
        }
        
        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println("删除key=1: " + cache.remove(1));
        System.out.println("size: " + cache.size());
        System.out.println("containsKey(1): " + cache.containsKey(1));
        
        cache.clear();
        System.out.println("clear后size: " + cache.size());
    }
    
    private static void testKeysMethod() {
        System.out.println("\n=== 测试keys()方法 ===");
        
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        
        System.out.print("keys(): ");
        for (Integer key : cache.keys()) {
            System.out.print(key + " ");
        }
        System.out.println();
        
        cache.get(1);
        System.out.print("get(1)后keys(): ");
        for (Integer key : cache.keys()) {
            System.out.print(key + " ");
        }
        System.out.println();
    }
    
    private static void testConcurrentCache() {
        System.out.println("\n=== 测试线程安全版本 ===");
        
        ConcurrentLRUCache<Integer, String> cache = new ConcurrentLRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        
        cache.printCache();
        System.out.println("size: " + cache.size());
        System.out.println("containsKey(2): " + cache.containsKey(2));
        
        cache.get(1);
        cache.printCache();
        
        cache.remove(2);
        System.out.println("remove(2)后size: " + cache.size());
        
        cache.clear();
        System.out.println("clear后size: " + cache.size());
    }
}