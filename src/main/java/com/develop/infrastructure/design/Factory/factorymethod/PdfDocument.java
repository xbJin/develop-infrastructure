package com.develop.infrastructure.design.Factory.factorymethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:13:08
 * @description :
 */
public class PdfDocument implements Document{
    @Override
    public void open() {
        System.out.println("打开PDF文档");
    }

    @Override
    public void save() {
        System.out.println("保存PDF文档");
    }
}
