package model;

public class Plan {

    private int id;
    private String name;
    private double cost;
    private double localData;
    private double globalData;
    private int call;
    private int messages;

    public Plan() {
    }

    public Plan(String name, double cost, double localTraffic,
                double globalTraffic, int call, int messages) {
        this.name = name;
        this.cost = cost;
        this.localData = localTraffic;
        this.globalData = globalTraffic;
        this.call = call;
        this.messages = messages;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getLocalData() {
        return localData;
    }

    public void setLocalData(double localData) {
        this.localData = localData;
    }

    public double getGlobalData() {
        return globalData;
    }

    public void setGlobalData(double globalData) {
        this.globalData = globalData;
    }

    public int getCall() {
        return call;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}
