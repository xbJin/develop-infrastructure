package com.develop.infrastructure.design.Factory.factorymethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:21:52
 * @description :
 */
public class WordDocumentFactory extends DocumentFactory {
    @Override
    protected Document createDocument() {
        return new WordDocument();
    }
}
