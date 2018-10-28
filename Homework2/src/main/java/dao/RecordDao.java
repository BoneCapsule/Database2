package dao;

import model.CallRecord;
import model.DataEnum.DataType;
import model.DataRecord;
import model.SMSRecord;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordDao {

    /**
     * 插入流量使用记录
     * @param id
     * @param startTime
     * @param endTime
     * @param number
     * @param dataType
     */
    void insertDataRecord(@Param("id") int id, @Param("startTime") LocalDateTime startTime,
                       @Param("endTime") LocalDateTime endTime, @Param("number") double number,
                       @Param("dataType") String dataType);


    /**
     * 获取流量记录
     * @param id
     * @param nowTime
     * @return
     */
    List<DataRecord> getDataRecord(@Param("id") int id, @Param("nowTime") LocalDateTime nowTime);


    /**
     * 插入短信使用记录
     * @param id
     * @param number
     * @param sendTime
     */
    void insertSMSRecord(@Param("id") int id, @Param("number") int number, @Param("sendTime") LocalDateTime sendTime);


    /**
     * 获取短信使用记录
     * @param id
     * @param nowTime
     * @return
     */
    List<SMSRecord> getSMSRecord(@Param("id") int id, @Param("nowTime") LocalDateTime nowTime);


    /**
     * 插入电话使用记录
     * @param id
     * @param number
     * @param startTime
     * @param endTime
     */
    void insertCallRecord(@Param("id") int id, @Param("number") int number,
                          @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    /**
     * 获取电话使用记录
     * @param id
     * @param nowTime
     * @return
     */
    List<CallRecord> getCallRecord(@Param("id") int id, @Param("nowTime") LocalDateTime nowTime);
}
