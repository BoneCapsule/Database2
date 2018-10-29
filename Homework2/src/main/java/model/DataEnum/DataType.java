package model.DataEnum;

public enum DataType {
    LOCAL("本地流量"), GLOBAL("全国流量");
    public final String value;

    DataType(String value) {
        this.value = value;
    }
}
