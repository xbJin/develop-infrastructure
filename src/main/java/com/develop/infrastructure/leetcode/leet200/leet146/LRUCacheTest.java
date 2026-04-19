package com.develop.infrastructure.leetcode.leet200.leet146;

public class LRUCacheTest {
    public static void main(String[] args) {
        // 测试泛型版本
        testGenericCache();
        testEdgeCases();
    }
    
    private static void testGenericCache() {
        System.out.println("=== 测试泛型LRU缓存 ===");
        
        // 整数键值对
        LRUCache<Integer, String> cache1 = new LRUCache<>(3);
        cache1.put(1, "A");
        cache1.put(2, "B");
        cache1.put(3, "C");
        cache1.printCache();  // 输出: {3=C} {2=B} {1=A}
        
        cache1.get(1);
        cache1.printCache();  // 输出: {1=A} {3=C} {2=B}
        
        // 字符串键值对
        LRUCache<String, Integer> cache2 = new LRUCache<>(2);
        cache2.put("apple", 10);
        cache2.put("banana", 20);
        cache2.put("cherry", 30);
        System.out.println("banana: " + cache2.get("banana"));  // 应为null
        System.out.println("cherry: " + cache2.get("cherry"));  // 应为30
    }
    
    private static void testEdgeCases() {
        System.out.println("\n=== 测试边界情况 ===");
        
        // 测试容量为1
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, "One");
        System.out.println("get(1): " + cache.get(1));  // One
        
        cache.put(2, "Two");
        System.out.println("get(1): " + cache.get(1));  // null
        System.out.println("get(2): " + cache.get(2));  // Two
        
        // 测试null检查
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
        
        // 测试删除
        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println("删除key=1: " + cache.remove(1));  // A
        System.out.println("size: " + cache.size());  // 1
        System.out.println("containsKey(1): " + cache.containsKey(1));  // false
        
        // 测试清空
        cache.clear();
        System.out.println("clear后size: " + cache.size());  // 0
    }
}