package model;

public class User {

    private String id;
    private String name;
    private double charge;  // 本月话费
    private double balance;

    public User() {
    }

    public User(String id, String name, double charge, double balance) {
        this.id = id;
        this.name = name;
        this.charge = charge;
        this.balance = balance;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
