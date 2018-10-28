package model;

import java.time.LocalDateTime;

public class HistoryPlan {

    private int id;
    private int pid;
    private LocalDateTime orderTime;

    public HistoryPlan() {
    }

    @Override
    public String toString() {
        return "用户ID：" + id +
                ", 套餐ID：" + pid +
                ", 订购时间：" + orderTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
