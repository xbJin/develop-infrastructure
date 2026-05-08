package com.develop.infrastructure.design.TemplateMethod.common;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 22:40:46
 * @description : 模版方法 - 制作饮料
 */
public abstract class BeverageMaker {

    /**
     * 制作饮料的模版方法
     * final 防止子类重写
     */
    public final void makeBeverage(){
        // 加水
        boilWater();

        // 抽象方法
        brew();

        // 钩子方法
        if (customerWantsCondiments()){
            // 抽象方法
            addCondiments();
        }

        // 导入杯中
        pourInCup();

        // 通知客户
        serve();
    }


    private void boilWater(){
        System.out.println("加水...");
    }

    /**
     * 放入原材料 - 抽象方法,由子类实现
     */
    protected abstract void brew();

    /**
     * 添加调味品 - 子类实现
     */
    protected abstract void addCondiments();


    private void pourInCup(){
        System.out.println("导入杯中....");
    }

    /**
     * 递给客户 - 通用步骤
     */
    private void serve(){
        System.out.println("递给客户享用");
    }

    /**
     * 钩子方法，询问客户是否需要调味品,默认为true,子类可以重写
     * @return
     */
    protected boolean customerWantsCondiments(){
        return true;
    }

}
