package com.develop.infrastructure.design.Factory.simple;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:06:38
 * @description :
 */
public class SimpleFactory {

    public static Product createProduct(String type) {
        switch (type) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                throw new IllegalArgumentException("未知的产品类型: " + type);
        }
    }


    public static void main(String[] args) {
        Product a = SimpleFactory.createProduct("A");
        a.use();
        Product b = SimpleFactory.createProduct("B");
        b.use();
    }

}
