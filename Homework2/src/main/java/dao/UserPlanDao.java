package dao;

import model.HistoryPlan;
import model.UserPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserPlanDao {

    /**
     * 查找当前时刻用户使用中的套餐
     * @param id
     * @return
     */
    List<UserPlan> getUserPlan(@Param("id") int id);

    /**
     * 查找历史套餐
     * @return
     */
    List<HistoryPlan> getHistoryPlan(@Param("id") int id, @Param("nowTime") LocalDateTime nowTime);

    void deleteUserPlan(@Param("id") int id, @Param("pid") int pid);

    void insertUserPlan(@Param("id") int id, @Param("pid") int pid);
}
