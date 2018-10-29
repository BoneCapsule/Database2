package model.bill;

import model.UserPlan;

import java.time.LocalDate;
import java.util.List;

public class Bill {
    private int id;
    private String name;
    private double totalCharge;            // 总话费
    private List<UserPlan> userPlans;

    private LocalDate startDate;  // 开始日期
    private LocalDate endDate;    // 结束日期

    private RecordBill callBill;
    private RecordBill smsBill;
    private RecordBill localDataBill;
    private RecordBill globalDataBill;

    public Bill() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("套餐");
        sb.append(userPlans.get(0).getPid());
        for (int i = 1; i < userPlans.size(); i++) {
            sb.append(", ");
            sb.append("套餐");
            sb.append(userPlans.get(i).getPid());
        }
        String ls = System.lineSeparator();
        return "账单："  + ls +
                "用户ID：" + id + ls +
                "用户姓名：" + name + ls +
                "本月话费：" + totalCharge + "元" + ls +
                "用户正在使用的套餐：" + sb.toString() + ls +
                "开始日期：" + startDate + ls +
                "结束日期：" + endDate + ls +
                "电话使用情况：" + callBill.toString("分钟") + ls +
                "短信使用情况：" + smsBill.toString("条") + ls +
                "本地流量使用情况：" + localDataBill.toString("M") + ls +
                "国内流量使用情况：" + globalDataBill.toString("M")
                ;
    }

    private String UserPlan() {
        StringBuilder sb = new StringBuilder();
        sb.append(userPlans.get(0).getPid());
        for (int i = 1; i < userPlans.size(); i++) {
            sb.append(", ");
            sb.append(userPlans.get(i).getPid());
        }
        return sb.toString();
    }

    public double getTotalCharge() {
        return this.totalCharge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setUserPlans(List<UserPlan> userPlans) {
        this.userPlans = userPlans;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public RecordBill getCallBill() {
        return callBill;
    }

    public void setCallBill(RecordBill callBill) {
        this.callBill = callBill;
    }

    public RecordBill getSmsBill() {
        return smsBill;
    }

    public void setSmsBill(RecordBill smsBill) {
        this.smsBill = smsBill;
    }

    public RecordBill getLocalDataBill() {
        return localDataBill;
    }

    public void setLocalDataBill(RecordBill localDataBill) {
        this.localDataBill = localDataBill;
    }

    public RecordBill getGlobalDataBill() {
        return globalDataBill;
    }

    public void setGlobalDataBill(RecordBill globalDataBill) {
        this.globalDataBill = globalDataBill;
    }
}
