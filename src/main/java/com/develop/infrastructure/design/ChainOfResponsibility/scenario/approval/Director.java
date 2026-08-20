package com.develop.infrastructure.design.ChainOfResponsibility.scenario.approval;

/**
 * 总监审批人
 *
 * 审批权限：
 *   - 请假：7天以内
 *   - 报销：20000元以内
 */
public class Director extends Approver {

    public Director(String name) {
        super(name);
    }

    @Override
    public void approve(ApprovalRequest request) {
        boolean canApprove = false;

        if ("LEAVE".equals(request.getType()) && request.getAmount() <= 7) {
            canApprove = true;
        } else if ("EXPENSE".equals(request.getType()) && request.getAmount() <= 20000) {
            canApprove = true;
        }

        if (canApprove) {
            request.setResult(String.format("总监[%s]已审批通过 - %s", name, request));
            System.out.println(request.getResult());
        } else if (next != null) {
            System.out.printf("总监[%s]权限不足，转交上级审批 - %s%n", name, request);
            next.approve(request);
        } else {
            request.setResult("审批失败：无上级审批人可处理 - " + request);
            System.out.println(request.getResult());
        }
    }
}
