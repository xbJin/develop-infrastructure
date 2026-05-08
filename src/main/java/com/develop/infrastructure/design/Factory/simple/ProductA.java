package com.develop.infrastructure.design.Factory.simple;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:04:24
 * @description :
 */
public class ProductA implements Product{

    @Override
    public void use() {
        System.out.println("使用产品A");
    }

}
