package model;

public class BasicCharge {

    private int id;
    private double callCharge;
    private double SMSCharge;
    private double localDataCharge;
    private double globalDataCharge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCallCharge() {
        return callCharge;
    }

    public void setCallCharge(double callCharge) {
        this.callCharge = callCharge;
    }

    public double getSMSCharge() {
        return SMSCharge;
    }

    public void setSMSCharge(double SMSCharge) {
        this.SMSCharge = SMSCharge;
    }

    public double getLocalDataCharge() {
        return localDataCharge;
    }

    public void setLocalDataCharge(double localDataCharge) {
        this.localDataCharge = localDataCharge;
    }

    public double getGlobalDataCharge() {
        return globalDataCharge;
    }

    public void setGlobalDataCharge(double globalDataCharge) {
        this.globalDataCharge = globalDataCharge;
    }
}
