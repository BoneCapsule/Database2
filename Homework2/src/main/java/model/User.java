package model;

public class User {

    private int id;
    private String name;
    private double charge;  // 本月话费
    private double balance;

    public User() {
    }

    public User(String name, double charge, double balance) {
        this.name = name;
        this.charge = charge;
        this.balance = balance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
