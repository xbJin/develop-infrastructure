package com.develop.infrastructure.design.Factory.factorymethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:17:35
 * @description :
 */
public abstract class DocumentFactory {

    /**
     * 工厂方法 - 子类实现此方法创建具体产品
     * @return
     */
    protected abstract Document createDocument();

    /**
     * 框架方法 - 使用工厂方法创建的产品来完成具体逻辑
     * @return
     */
    public Document newDocument(){
        Document document = createDocument();
        System.out.println("文档创建成功");
        return document;
    }

}
