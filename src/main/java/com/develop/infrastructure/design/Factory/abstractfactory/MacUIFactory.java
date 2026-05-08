package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:34:36
 * @description : Mac风格工厂 - 生产Mac风格一族的UI组件
 */
public class MacUIFactory implements UIFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextField createTextField() {
        return new MacTextField();
    }
}
