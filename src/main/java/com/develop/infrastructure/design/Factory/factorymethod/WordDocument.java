package com.develop.infrastructure.design.Factory.factorymethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:14:31
 * @description :
 */
public class WordDocument implements Document{
    @Override
    public void open() {
        System.out.println("打开Word文档");
    }

    @Override
    public void save() {
        System.out.println("保存Word文档");
    }

}
