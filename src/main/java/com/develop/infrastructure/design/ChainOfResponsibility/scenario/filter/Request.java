package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * 请求对象
 */
public class Request {

    /** 请求路径 */
    private String path;

    /** 请求头信息 */
    private StringBuilder headers = new StringBuilder();

    /** 请求体 */
    private String body;

    public Request(String path, String body) {
        this.path = path;
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void addHeader(String header) {
        headers.append(header).append("; ");
    }

    public String getHeaders() {
        return headers.toString();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
