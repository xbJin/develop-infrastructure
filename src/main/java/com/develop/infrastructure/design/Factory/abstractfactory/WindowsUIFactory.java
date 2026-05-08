package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:35:15
 * @description :Windows风格工厂 - 生产Windows风格一族的UI组件
 */
public class WindowsUIFactory implements UIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextField createTextField() {
        return new WindowsTextField();
    }
}
