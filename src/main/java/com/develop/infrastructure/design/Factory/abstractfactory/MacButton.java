package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:30:44
 * @description :
 */
public class MacButton implements Button{
    @Override
    public void render() {
        System.out.println("Mac Button");
    }
}
