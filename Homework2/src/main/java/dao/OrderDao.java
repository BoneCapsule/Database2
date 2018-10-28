package dao;

import model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface OrderDao {

    void insertOrder(@Param("id") int id, @Param("pid") int pid,
                     @Param("orderTime") LocalDateTime orderTime);

    /**
     * 用户订购套餐, 立即生效
     * @param id
     * @param pid
     */
    void OrderPlan(@Param("id") int id, @Param("pid") int pid);
}
