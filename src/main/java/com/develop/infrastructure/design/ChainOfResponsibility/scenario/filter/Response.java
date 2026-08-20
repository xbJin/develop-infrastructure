package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * 响应对象
 */
public class Response {

    /** 响应状态码 */
    private int status = 200;

    /** 响应头 */
    private StringBuilder headers = new StringBuilder();

    /** 响应体 */
    private String body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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