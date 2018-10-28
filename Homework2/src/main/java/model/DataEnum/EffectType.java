package model.DataEnum;

public enum EffectType {
    IMMEDIATE("立即生效"),       // 立即生效
    DELAY("下月生效");           // 延迟生效

    public final String value;

    EffectType(String value) {
        this.value = value;
    }
}
