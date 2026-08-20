package com.develop.infrastructure.design.ChainOfResponsibility.scenario.approval;

/**
 * 审批流程演示（纯责任链应用场景）
 *
 * 审批流程是纯责任链最典型的应用场景：
 *   - 请求沿着审批链逐级上报
 *   - 每个审批人根据权限决定是否处理
 *   - 请求最终只被一个审批人处理
 */
public class ApprovalDemo {

    public static void main(String[] args) {
        System.out.println("===== 审批流程演示（纯责任链应用场景） =====\n");

        // 1. 创建审批人
        Approver teamLeader = new TeamLeader("张组长");
        Approver manager = new Manager("李经理");
        Approver director = new Director("王总监");
        Approver ceo = new CEO("赵CEO");

        // 2. 组装审批链：组长 -> 经理 -> 总监 -> CEO
        teamLeader.setNext(manager).setNext(director).setNext(ceo);

        System.out.println("审批链：组长 -> 经理 -> 总监 -> CEO\n");

        // 3. 场景1：请假0.5天（组长可审批）
        System.out.println("--- 场景1：请假0.5天 ---");
        teamLeader.approve(new ApprovalRequest("小王", "LEAVE", 0.5));

        // 4. 场景2：请假2天（经理审批）
        System.out.println("\n--- 场景2：请假2天 ---");
        teamLeader.approve(new ApprovalRequest("小李", "LEAVE", 2));

        // 5. 场景3：请假5天（总监审批）
        System.out.println("\n--- 场景3：请假5天 ---");
        teamLeader.approve(new ApprovalRequest("小张", "LEAVE", 5));

        // 6. 场景4：请假10天（CEO审批）
        System.out.println("\n--- 场景4：请假10天 ---");
        teamLeader.approve(new ApprovalRequest("小陈", "LEAVE", 10));

        // 7. 场景5：报销300元（组长审批）
        System.out.println("\n--- 场景5：报销300元 ---");
        teamLeader.approve(new ApprovalRequest("小王", "EXPENSE", 300));

        // 8. 场景6：报销8000元（总监审批）
        System.out.println("\n--- 场景6：报销8000元 ---");
        teamLeader.approve(new ApprovalRequest("小李", "EXPENSE", 8000));

        // 9. 场景7：报销50000元（CEO审批）
        System.out.println("\n--- 场景7：报销50000元 ---");
        teamLeader.approve(new ApprovalRequest("小张", "EXPENSE", 50000));

        System.out.println("\n===== 审批流程演示结束 =====");
    }
}
