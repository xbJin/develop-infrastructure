package com.develop.infrastructure.design.TemplateMethod;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:15:13
 * @description :
 */
public class TextFileProcess extends FileProcess{

    /**
     * 文件校验
     * @param content
     * @return
     */
    @Override
    protected boolean validate(String content) {
        boolean valid = content !=null && !content.isEmpty();
        return valid;
    }

    @Override
    protected String procesData(String content) {
        int length = content.length();
        return "处理文件长度"+length;
    }
}
