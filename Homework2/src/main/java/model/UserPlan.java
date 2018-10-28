package model;

public class UserPlan {
    private int id;
    private int pid;

    @Override
    public String toString() {
        return "用户ID: " + id + ", 套餐ID: " + pid ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
