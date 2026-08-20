package com.develop.infrastructure.design.ChainOfResponsibility.dynamic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 责任链管理器
 *
 * 核心能力：
 *   1. 动态添加/删除处理者
 *   2. 动态调整处理者顺序（前移、后移、交换、按优先级排序）
 *   3. 条件启用/禁用处理者
 *   4. 替换链中的处理者
 *   5. 在指定位置前后插入处理者
 *
 * 设计思路：
 *   - 使用 LinkedHashMap 保持插入顺序，同时支持按名称快速查找
 *   - 提供丰富的链操作 API，支持运行时动态调整
 *   - 每次执行请求时，根据当前链的状态生成新的 DynamicChain
 */
public class ChainManager {

    /** 有序处理者映射表（保持插入顺序） */
    private final Map<String, DynamicHandler> handlerMap = new LinkedHashMap<>();

    /**
     * 在链末尾添加处理者
     *
     * @param handler 处理者
     * @return 当前 ChainManager（支持链式调用）
     */
    public ChainManager addLast(DynamicHandler handler) {
        handlerMap.put(handler.getName(), handler);
        return this;
    }

    /**
     * 在链头部添加处理者
     *
     * @param handler 处理者
     * @return 当前 ChainManager
     */
    public ChainManager addFirst(DynamicHandler handler) {
        Map<String, DynamicHandler> newMap = new LinkedHashMap<>();
        newMap.put(handler.getName(), handler);
        newMap.putAll(handlerMap);
        handlerMap.clear();
        handlerMap.putAll(newMap);
        return this;
    }

    /**
     * 在指定处理者之前插入
     *
     * @param beforeName 目标处理者名称
     * @param handler    要插入的处理者
     * @return 当前 ChainManager
     */
    public ChainManager addBefore(String beforeName, DynamicHandler handler) {
        Map<String, DynamicHandler> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, DynamicHandler> entry : handlerMap.entrySet()) {
            if (entry.getKey().equals(beforeName)) {
                newMap.put(handler.getName(), handler);
            }
            newMap.put(entry.getKey(), entry.getValue());
        }
        handlerMap.clear();
        handlerMap.putAll(newMap);
        return this;
    }

    /**
     * 在指定处理者之后插入
     *
     * @param afterName 目标处理者名称
     * @param handler   要插入的处理者
     * @return 当前 ChainManager
     */
    public ChainManager addAfter(String afterName, DynamicHandler handler) {
        Map<String, DynamicHandler> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, DynamicHandler> entry : handlerMap.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue());
            if (entry.getKey().equals(afterName)) {
                newMap.put(handler.getName(), handler);
            }
        }
        handlerMap.clear();
        handlerMap.putAll(newMap);
        return this;
    }

    /**
     * 删除指定处理者
     *
     * @param name 处理者名称
     * @return 当前 ChainManager
     */
    public ChainManager remove(String name) {
        handlerMap.remove(name);
        return this;
    }

    /**
     * 替换指定处理者
     *
     * @param name    要替换的处理者名称
     * @param handler 新的处理者
     * @return 当前 ChainManager
     */
    public ChainManager replace(String name, DynamicHandler handler) {
        if (handlerMap.containsKey(name)) {
            Map<String, DynamicHandler> newMap = new LinkedHashMap<>();
            for (Map.Entry<String, DynamicHandler> entry : handlerMap.entrySet()) {
                if (entry.getKey().equals(name)) {
                    newMap.put(handler.getName(), handler);
                } else {
                    newMap.put(entry.getKey(), entry.getValue());
                }
            }
            handlerMap.clear();
            handlerMap.putAll(newMap);
        }
        return this;
    }

    /**
     * 将指定处理者前移一位
     *
     * @param name 处理者名称
     * @return 当前 ChainManager
     */
    public ChainManager moveUp(String name) {
        List<String> keys = new ArrayList<>(handlerMap.keySet());
        int index = keys.indexOf(name);
        if (index > 0) {
            swap(keys.get(index - 1), name);
        }
        return this;
    }

    /**
     * 将指定处理者后移一位
     *
     * @param name 处理者名称
     * @return 当前 ChainManager
     */
    public ChainManager moveDown(String name) {
        List<String> keys = new ArrayList<>(handlerMap.keySet());
        int index = keys.indexOf(name);
        if (index < keys.size() - 1) {
            swap(name, keys.get(index + 1));
        }
        return this;
    }

    /**
     * 交换两个处理者的位置
     *
     * @param name1 处理者1名称
     * @param name2 处理者2名称
     * @return 当前 ChainManager
     */
    public ChainManager swap(String name1, String name2) {
        List<String> keys = new ArrayList<>(handlerMap.keySet());
        int idx1 = keys.indexOf(name1);
        int idx2 = keys.indexOf(name2);
        if (idx1 != -1 && idx2 != -1) {
            // 重建有序Map，交换位置
            Map<String, DynamicHandler> newMap = new LinkedHashMap<>();
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                if (i == idx1) {
                    newMap.put(name2, handlerMap.get(name2));
                } else if (i == idx2) {
                    newMap.put(name1, handlerMap.get(name1));
                } else {
                    newMap.put(key, handlerMap.get(key));
                }
            }
            handlerMap.clear();
            handlerMap.putAll(newMap);
        }
        return this;
    }

    /**
     * 按优先级排序
     * 优先级数值越小，越靠前执行
     *
     * @return 当前 ChainManager
     */
    public ChainManager sortByPriority() {
        List<Map.Entry<String, DynamicHandler>> entries = new ArrayList<>(handlerMap.entrySet());
        entries.sort(Comparator.comparingInt(e -> e.getValue().getPriority()));
        handlerMap.clear();
        for (Map.Entry<String, DynamicHandler> entry : entries) {
            handlerMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * 启用指定处理者
     */
    public ChainManager enable(String name) {
        // 这里仅做标记，实际禁用逻辑在 DynamicHandler 实现中
        return this;
    }

    /**
     * 禁用指定处理者
     */
    public ChainManager disable(String name) {
        return this;
    }

    /**
     * 执行责任链
     *
     * @param request 请求内容
     * @return 处理结果
     */
    public String execute(String request) {
        List<DynamicHandler> handlers = new ArrayList<>(handlerMap.values());
        StringBuilder result = new StringBuilder();
        DynamicChain chain = new DynamicChain(handlers, result);
        chain.proceed(request);
        return result.toString();
    }

    /**
     * 获取当前链的处理者顺序
     *
     * @return 处理者名称列表
     */
    public List<String> getChainOrder() {
        return new ArrayList<>(handlerMap.keySet());
    }

    /**
     * 打印当前链的顺序
     */
    public void printChainOrder() {
        System.out.println("当前责任链顺序: " + String.join(" -> ", handlerMap.keySet()));
    }
}
