package com.develop.infrastructure.design.TemplateMethod.sort;

import java.util.Arrays;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 22:49:22
 * @description : 排序算法的模版方法,定义通用的骨架
 */
public abstract class SortAlgorithmTemplate {

    /**
     *
     */
    public final void algorithmTemplate(int[] arr){
        // 前置检查失败
        if (!beforeSort(arr)){
            return;
        }

        long start = System.currentTimeMillis();

        doSort(arr);

        long end = System.currentTimeMillis();

        if (validate(arr)){
            System.out.println("排序验证成功,排序算法耗时:" + (end -start) + "ms");
        } else {
            System.out.println("排序算法验证失败");
        }

    }

    /**
     * 前置检查 - 钩子方法
     * @return
     */
    protected boolean beforeSort(int[] arr){
        if (arr == null || arr.length <= 1){
            System.out.println("排序前置检查失败");
            return false;
        }
        System.out.println("开始排序,数组:"+ Arrays.toString(arr));
        return true;
    }

    /**
     * 抽象排序方法,由子类实现
     * @param arr
     */
    protected abstract void doSort(int[] arr);


    protected boolean validate(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }

}
