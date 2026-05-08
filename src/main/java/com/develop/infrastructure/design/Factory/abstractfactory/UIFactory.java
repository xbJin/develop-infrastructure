package com.develop.infrastructure.design.Factory.abstractfactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 21:32:07
 * @description : 抽象工厂 - 创建一族产品的接口,每个工厂生产同一风格的产品族
 */
public interface UIFactory {

    /**
     * 生产按钮
     * @return
     */
    Button createButton();

    /**
     * 生产文本框
     * @return
     */
    TextField createTextField();

}
