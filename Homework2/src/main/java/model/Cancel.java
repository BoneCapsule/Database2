package model;

import model.DataEnum.EffectType;

import java.time.LocalDateTime;

public class Cancel {
    private int userId;
    private int planId;
    private LocalDateTime cancelTime;
    private LocalDateTime effectTime;  // 套餐退订的生效时间
    private EffectType effectType;


    public Cancel() {
        if (effectType == EffectType.IMMEDIATE) {
            effectTime = cancelTime;
        } else {
            effectTime = cancelTime.plusMonths(1);
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
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
