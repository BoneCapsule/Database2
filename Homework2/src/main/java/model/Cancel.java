package model;

import model.DataEnum.EffectType;

import java.time.LocalDateTime;

public class Cancel {
    private int id;
    private int pid;
    private LocalDateTime cancelTime;
    private LocalDateTime effectTime;  // 套餐退订的生效时间
    private EffectType effectType;


    public Cancel() {
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

    public LocalDateTime getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(LocalDateTime cancelTime) {
        this.cancelTime = cancelTime;
    }

    public LocalDateTime getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(LocalDateTime effectTime) {
        this.effectTime = effectTime;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }
}
