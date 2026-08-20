package com.develop.infrastructure.design.ChainOfResponsibility.scenario.approval;

/**
 * 组长审批人
 *
 * 审批权限：
 *   - 请假：1天以内
 *   - 报销：500元以内
 */
public class TeamLeader extends Approver {

    public TeamLeader(String name) {
        super(name);
    }

    @Override
    public void approve(ApprovalRequest request) {
        boolean canApprove = false;

        if ("LEAVE".equals(request.getType()) && request.getAmount() <= 1) {
            canApprove = true;
        } else if ("EXPENSE".equals(request.getType()) && request.getAmount() <= 500) {
            canApprove = true;
        }

        if (canApprove) {
            request.setResult(String.format("组长[%s]已审批通过 - %s", name, request));
            System.out.println(request.getResult());
        } else if (next != null) {
            System.out.printf("组长[%s]权限不足，转交上级审批 - %s%n", name, request);
            next.approve(request);
        } else {
            request.setResult("审批失败：无上级审批人可处理 - " + request);
            System.out.println(request.getResult());
        }
    }
}