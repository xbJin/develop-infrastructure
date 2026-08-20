package com.develop.infrastructure.design.ChainOfResponsibility.scenario.approval;

/**
 * CEO审批人
 *
 * 审批权限：无限制（链的末端，处理所有无法被下级处理的请求）
 */
public class CEO extends Approver {

    public CEO(String name) {
        super(name);
    }

    @Override
    public void approve(ApprovalRequest request) {
        // CEO拥有最高审批权限，可以审批任何请求
        request.setResult(String.format("CEO[%s]已审批通过 - %s", name, request));
        System.out.println(request.getResult());
    }
}