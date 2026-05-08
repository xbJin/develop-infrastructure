package com.develop.infrastructure.design.Factory.factorymethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:15:58
 * @description :
 */
public class PdfDocumentFactory extends DocumentFactory{

    @Override
    protected Document createDocument() {
        return new PdfDocument();
    }

}
