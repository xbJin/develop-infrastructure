package com.develop.infrastructure.design.TemplateMethod.common;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 22:34:03
 * @description : 抽象模版类，定义算法骨架和抽象方法
 */
public abstract class AbstractClass {

    /**
     * 定义模版方法
     */
    public final void templateMethod(){
        init();

        // 子类实现
        doBusiness();

        // 可根据场景决定子类是否需要执行
        if (hook()){
            beforeEnd();
        }

        end();
    }

    /**
     * 初始化的方法 - 通用逻辑
     */
    protected void init(){
        System.out.println("AbstractClass 初始化...");
    }

    /**
     * 具体业务方法 - 抽象方法,由子类实现
     */
    protected abstract void doBusiness();

    /**
     * 结束方法 - 通用逻辑
     */
    protected void end(){
        System.out.println("AbstractClass 执行完成...");
    }

    /**
     * 钩子方法,子类可拓展重写
     * @return
     */
    protected boolean hook(){
        return true;
    }


    protected void beforeEnd(){
        System.out.println("AbstractClass 执行完成前处理...");
    }

}
