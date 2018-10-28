package model.bill;

public class RecordBill {

    private double totalUse;     // 总使用量
    private double charge;    // 总费用
    private double planTotal; // 套餐总量
    private double extra;     // 额外使用量
    private double remain;    // 套餐剩余量

    public RecordBill() {
    }

    public RecordBill(double totalUse, double charge, double planTotal, double extra, double remain) {
        this.totalUse = totalUse;
        this.charge = charge;
        this.planTotal = planTotal;
        this.extra = extra;
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "记录账单{" +
                "总使用量=" + totalUse +
                ", 费用=" + charge +
                ", 套餐总量=" + planTotal +
                ", 额外使用量=" + extra +
                ", 剩余量=" + remain +
                '}';
    }

    public double getTotalUse() {
        return totalUse;
    }

    public void setTotalUse(double totalUse) {
        this.totalUse = totalUse;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getPlanTotal() {
        return planTotal;
    }

    public void setPlanTotal(double planTotal) {
        this.planTotal = planTotal;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }
}
