package com.develop.infrastructure.design.ChainOfResponsibility.scenario.approval;

/**
 * 审批请求
 *
 * 封装审批所需的信息：
 *   - 申请人
 *   - 请求类型（请假/报销）
 *   - 数量（天数/金额）
 *   - 审批结果
 */
public class ApprovalRequest {

    /** 申请人 */
    private String applicant;

    /** 请求类型：LEAVE（请假）、EXPENSE（报销） */
    private String type;

    /** 数量：请假天数或报销金额 */
    private double amount;

    /** 审批结果 */
    private String result;

    public ApprovalRequest(String applicant, String type, double amount) {
        this.applicant = applicant;
        this.type = type;
        this.amount = amount;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("[审批请求] 申请人=%s, 类型=%s, 数量=%.1f", applicant, type, amount);
    }
}
