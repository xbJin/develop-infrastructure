package com.develop.infrastructure.design.Factory.factorymethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:23:02
 * @description : 工厂方法演示
 */
public class FactoryMethod {

    public static void main(String[] args) {
        PdfDocumentFactory pdfDocumentFactory = new PdfDocumentFactory();
        Document pdf = pdfDocumentFactory.newDocument();
        pdf.open();
        pdf.save();

        WordDocumentFactory wordDocumentFactory = new WordDocumentFactory();
        Document word = wordDocumentFactory.newDocument();
        word.open();
        word.save();
    }


}
