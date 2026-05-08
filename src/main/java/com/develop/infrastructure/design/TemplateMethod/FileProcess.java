package com.develop.infrastructure.design.TemplateMethod;

import io.micrometer.common.util.StringUtils;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:04:31
 * @description : 模版方法,处理文件
 */
public abstract class FileProcess {

    /**
     * 模版方法 - 处理文件的骨架
     */
    public final void process(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return;
        }
        String content = readFile(filePath);

        /**
         * 验证文件
         */
        if (validate(content)) {
            // 处理数据,子类实现
            String result = procesData(content);
            saveResult(result);
            logSuccess();
        } else {
            logError();
        }
    }

    /**
     * 读取文件 - 通用实现
     *
     * @return
     */
    protected String readFile(String filePath) {
        System.out.println("读取文件," + filePath);
        return "file content";
    }

    /**
     * 抽象方法 - 验证文件,子类完成
     *
     * @return
     */
    protected abstract boolean validate(String content);

    /**
     * 处理数据
     *
     * @param content
     * @return
     */
    protected abstract String procesData(String content);


    protected void beforeValidate() {
        System.out.println("执行验证前置检查");
    }

    protected void saveResult(String result) {
        System.out.println("保存结果");
    }

    /**
     * 记录日志,子类可以重写
     */
    protected void logSuccess() {
        System.out.println("记录日志");
    }

    /**
     * 子类可以重写
     */
    protected void logError() {
        System.out.println("记录错误");
    }


}
