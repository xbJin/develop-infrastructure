package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:36:12
 * @description : 客户端 - 客户端依赖抽象工厂，不关心产品的创建细节
 */
public class Application {

    private final Button button;
    private final TextField textField;

    public Application(UIFactory uiFactory){
        this.button = uiFactory.createButton();
        this.textField = uiFactory.createTextField();
    }

    public void renderUI(){
        button.render();
        textField.display();
    }

}
