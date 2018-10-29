package dao;

import model.BasicCharge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BasicChargeDao {

    BasicCharge getBasicCharge(@Param("id") int id);

    void insertBasicCharge(@Param("id") int id, @Param("call") double call, @Param("sms") double sms,
                           @Param("localData") double localData, @Param("globalData") double globalData);
}
