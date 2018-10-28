package dao;

import model.DataEnum.EffectType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface CancelDao {

    void CancelPlan(@Param("id") int userId, @Param("pid") int planId,
                    @Param("cancelTime") LocalDateTime cancelTime, @Param("effectType") String effectType);
}
