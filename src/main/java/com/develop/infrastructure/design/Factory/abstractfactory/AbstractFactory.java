package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:38:26
 * @description :
 */
public class AbstractFactory {

    public static void main(String[] args) {
        MacUIFactory macUIFactory = new MacUIFactory();
        Application macApp = new Application(macUIFactory);
        macApp.renderUI();

        System.out.println("-----------------------------");

        WindowsUIFactory windowsUIFactory = new WindowsUIFactory();
        Application windowsApp = new Application(windowsUIFactory);
        windowsApp.renderUI();
    }

}
