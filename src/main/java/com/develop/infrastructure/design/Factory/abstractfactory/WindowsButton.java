package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:31:22
 * @description :
 */
public class WindowsButton implements Button{
    @Override
    public void render() {
        System.out.println("Windows Button");
    }
}
