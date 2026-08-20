package com.develop.infrastructure.design.ChainOfResponsibility.scenario.approval;

/**
 * 审批人抽象类（纯责任链应用 - 审批流程）
 *
 * 场景说明：
 *   企业中的请假/报销审批流程是纯责任链的经典应用。
 *   不同金额/天数的申请需要不同级别的审批人处理：
 *   - 组长：可审批1天请假 / 500元以下报销
 *   - 经理：可审批3天以内请假 / 5000元以下报销
 *   - 总监：可审批7天以内请假 / 20000元以下报销
 *   - CEO：可审批任意天数/金额
 *
 * 纯责任链特征：
 *   - 一个审批请求只会被一个审批人处理
 *   - 如果当前审批人权限不足，则向上级传递
 */
public abstract class Approver {

    /** 审批人名称 */
    protected String name;

    /** 下一个审批人（上级） */
    protected Approver next;

    public Approver(String name) {
        this.name = name;
    }

    /**
     * 设置上级审批人
     */
    public Approver setNext(Approver next) {
        this.next = next;
        return next;
    }

    /**
     * 审批请求
     *
     * @param request 审批请求
     */
    public abstract void approve(ApprovalRequest request);
}
